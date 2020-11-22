package com.mercurit.apialerts.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercurit.apialerts.domain.Users;
import com.mercurit.apialerts.serviceImpl.UsersServiceImpl;

@RestController
@RequestMapping("/apialerts")
public class UserDetailsController 
{
	private final Logger log=LoggerFactory.getLogger(UserDetailsController.class);
	
	private  UsersServiceImpl usersServiceImpl;
	
	public UserDetailsController(UsersServiceImpl usersServiceImpl)
	{
		this.usersServiceImpl=usersServiceImpl;
	}
	
	@PostMapping("/register")
	public ResponseEntity<Users> registerUser(@Valid @RequestBody Users user) throws Exception
	{
		log.info("Entered UserDetailsController:registerUser");
		
		Users userResponse=usersServiceImpl.registerUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
	}
	@GetMapping("/AllUsers")
	public ResponseEntity<List<Users>> getAll()
	{
		return ResponseEntity.ok(usersServiceImpl.getAllActiveUserInfo());
	}
	
	@GetMapping("/byusername/{username}")
	public ResponseEntity<Optional<Users>> getUserByName(@PathVariable String username)
	{
		return ResponseEntity.ok(usersServiceImpl.getUserInfoByUserName(username));
	}
	
}
