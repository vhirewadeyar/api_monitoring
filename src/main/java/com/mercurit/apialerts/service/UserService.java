package com.mercurit.apialerts.service;

import java.util.List;
import java.util.Optional;

import com.mercurit.apialerts.domain.Users;



public interface UserService {
	
	public Optional<Users> getUserInfoByUserName(String userName);
	
	public List<Users> getAllActiveUserInfo();
	
//	public Users getUserInfoById(Integer id);
	
	public Users registerUser(Users user) throws Exception;
	
	

}
