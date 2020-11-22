package com.mercurit.apialerts.serviceImpl;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercurit.apialerts.domain.Users;
import com.mercurit.apialerts.errors.CustomExceptionError;
import com.mercurit.apialerts.repository.UserRepository;
import com.mercurit.apialerts.service.UserService;


@Service
@Transactional
public class UsersServiceImpl implements UserDetailsService,UserService
{
	@Autowired
	private UserRepository userRepository;
	private final Logger log=LoggerFactory.getLogger(UsersServiceImpl.class);
	
	@Autowired
	private  PasswordEncoder passwordEncoder;
	
	
	
	public UsersServiceImpl(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	

	
	@Override
	public Users registerUser(Users user) throws Exception{
		if(userRepository.findOneByMailIdIgnoreCase(user.getMailId()).isPresent()){
			throw new CustomExceptionError("Email Id exists");
		}
		if(userRepository.findOneByUserNameIgnoreCase(user.getUserName()).isPresent()){
			throw new CustomExceptionError("Username exists");
		}
		
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
//		String user=userRepository.sessionPresent(userName);
		Optional<Users> userInfo=userRepository.findOneByUserNameIgnoreCase(userName);
		
		return new User(userInfo.get().getUserName(),userInfo.get().getPassword(),new ArrayList<>());
	}	

	@Override
	public List<Users> getAllActiveUserInfo()
	{
		return userRepository.findAll();
	}
	
	
		
	@Override
	public Optional<Users> getUserInfoByUserName(String userName)
	{
		return userRepository.findOneByUserNameIgnoreCase(userName);
	}



//	@Override
//	public Users getUserInfoById(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
