package com.mercurit.apialerts.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.mercurit.apialerts.repository.UserRepository;
import com.mercurit.apialerts.security.JWTUtil;



@RestController
@RequestMapping("/apialerts")
public class LogoutController {
	
	@Autowired
	private JWTUtil jwtUtil;
	

	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@PostMapping("/logout/{username}")
	public ResponseEntity<?> logout(HttpServletRequest req,HttpServletResponse response,@PathParam("username")String username,HttpSession session){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name=authentication.getName();
		if(authentication!=null)
			new SecurityContextLogoutHandler().logout(req, response, authentication);
	//	userRepository.clrSession(name,"Expired");
		return ResponseEntity.ok("Logged out "+ name);
	}
	
	@GetMapping("/isTokenExpired")
	public ResponseEntity<?> isTokenExpired(@QueryParam("token")String token){
		return ResponseEntity.ok(jwtUtil.isTokenExpired(token));
		
	}

}
