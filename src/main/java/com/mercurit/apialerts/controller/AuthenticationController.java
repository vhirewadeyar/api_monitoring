package com.mercurit.apialerts.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercurit.apialerts.domain.Users;
import com.mercurit.apialerts.dto.AuthenticationRequest;
import com.mercurit.apialerts.errors.CustomExceptionError;
import com.mercurit.apialerts.repository.UserRepository;
import com.mercurit.apialerts.security.JWTUtil;
import com.mercurit.apialerts.serviceImpl.UsersServiceImpl;

@RestController
@RequestMapping("/apialerts")
public class AuthenticationController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private HttpServletRequest httpRequest;

	
	@Autowired
	UsersServiceImpl userServiceImpl;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JWTUtil JwtUtil;

	@PostMapping(value="/login")
	public ResponseEntity<?> createAuthenticateToken(@RequestBody AuthenticationRequest request) throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
		}
		catch(BadCredentialsException e) {
			System.out.println(e);
			throw new CustomExceptionError("Invalid crendentials");
		}
//		if(request.getUsername().equalsIgnoreCase(sessionHolder))
//		{
//			throw new UsernameNotFoundException("Already login session exists");
//		}
		Optional<Users> userDb=userRepository.findOneByUserNameIgnoreCase(request.getUsername());
		String loginSession=userRepository.getSession(request.getUsername());
		if(loginSession!=null && (new BCryptPasswordEncoder().matches(request.getPassword(),userDb.get().getPassword() ))) 
		{
			throw new UsernameNotFoundException("Already login session exists");
		}
		
		HttpSession session=httpRequest.getSession();
		session.setAttribute(request.getUsername().toLowerCase(), request.getUsername().toLowerCase());
		
		final Optional<Users> userDetails=userServiceImpl.getUserInfoByUserName(request.getUsername());
		
		final String JWT=JwtUtil.generateToken(userDetails.get());
		
		return ResponseEntity.ok(new AuthResponse(JWT));
		}
	
		public void sessionValidate(HttpServletRequest request,HttpServletResponse response) {
			
		}
	
	
	static class AuthResponse{
		private String jwtToken;
		
		AuthResponse(String jwtToken){
			this.jwtToken=jwtToken;
		}

		public String getJwtToken() {
			return jwtToken;
		}

		public void setJwtToken(String jwtToken) {
			this.jwtToken = jwtToken;
		}
		
		
		
	}
}
