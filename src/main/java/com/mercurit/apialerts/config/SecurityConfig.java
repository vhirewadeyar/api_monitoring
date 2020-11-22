package com.mercurit.apialerts.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mercurit.apialerts.security.JWTRequestFilter;
import com.mercurit.apialerts.serviceImpl.UsersServiceImpl;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	private UsersServiceImpl userServiceImpl;
	 
	
	private JWTRequestFilter jwtRequestFilter;
	
	private AuthenticationManagerBuilder authenticationManagerBuilder;
	
	public SecurityConfig(UsersServiceImpl userServiceImpl,JWTRequestFilter jwtRequestFilter
			,AuthenticationManagerBuilder authenticationManagerBuilder) {
		this.userServiceImpl=userServiceImpl;
		this.jwtRequestFilter=jwtRequestFilter;
		this.authenticationManagerBuilder=authenticationManagerBuilder;
	}
	
	
	@PostConstruct
	public void init() throws Exception{
		authenticationManagerBuilder.userDetailsService(userServiceImpl)
		.passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder oauth) throws Exception
	{
		oauth.userDetailsService(userServiceImpl);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests().antMatchers("/apialerts/login").permitAll()
		.antMatchers("/apialerts/register").permitAll()
		.anyRequest().authenticated()
		.and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
}
