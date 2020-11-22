package com.mercurit.apialerts.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mercurit.apialerts.serviceImpl.UsersServiceImpl;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	UsersServiceImpl userServiceImpl;
	
	@Autowired
	JWTUtil JwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain)
	throws ServletException,IOException{
		
		final String authrizationHeader=request.getHeader("Authorization");
		
		String username=null;
		String jwt=null;
		
	
	
	if(authrizationHeader!=null && authrizationHeader.startsWith("Bearer ")) {
		jwt=authrizationHeader.substring(7);
		username=JwtUtil.extractUsername(jwt); 
	}
	
	if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
		UserDetails userDetails=this.userServiceImpl.loadUserByUsername(username);
		if(JwtUtil.validateToken(jwt, userDetails)) {
			UsernamePasswordAuthenticationToken usernameAuth=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			usernameAuth.setDetails((new WebAuthenticationDetailsSource().buildDetails(request)));
			SecurityContextHolder.getContext().setAuthentication(usernameAuth);
			
		}
	}
	chain.doFilter(request, response);
}

}
