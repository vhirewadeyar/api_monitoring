package com.mercurit.apialerts.security;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public final class SecurityUtil {
	
	private SecurityUtil() {
		
	}
	
	
	public static Optional<String> getCurrentUserLogin(){
		SecurityContext securityContext=SecurityContextHolder.getContext();
		return Optional.ofNullable(securityContext.getAuthentication())
				.map(authentication -> {
					if(authentication.getPrincipal() instanceof UserDetails) {
						UserDetails springSecurityUser=(UserDetails) authentication.getPrincipal();
						return springSecurityUser.getUsername();
					}else if(authentication.getPrincipal() instanceof String) {
						return (String)authentication.getPrincipal();
					}
					return null;
				}); 
	}

	
}
