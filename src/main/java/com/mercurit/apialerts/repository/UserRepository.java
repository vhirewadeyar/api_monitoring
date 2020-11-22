package com.mercurit.apialerts.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mercurit.apialerts.domain.Users;


public interface UserRepository extends JpaRepository<Users,String>
{
	
	
	Optional<Users> findOneByMailIdIgnoreCase(String mail);
	
	Optional<Users> findOneByUserNameIgnoreCase(String userName);
	
	
	@Query(value="select SESSION_PRIMARY_ID from SPRING_SESSION_ATTRIBUTES where ATTRIBUTE_NAME=:login",nativeQuery=true)
	String getSession(@Param(value="login")String login);
	
	@Modifying
    @Transactional
    @Query(value ="DELETE SPRING_SESSION_ATTRIBUTES, SPRING_SESSION  FROM SPRING_SESSION_ATTRIBUTES  INNER JOIN SPRING_SESSION WHERE SPRING_SESSION_ATTRIBUTES.SESSION_PRIMARY_ID= SPRING_SESSION.PRIMARY_ID and SPRING_SESSION_ATTRIBUTES.ATTRIBUTE_NAME=:login",nativeQuery=true)
    //@Query(value ="DELETE spring_session_attributes, spring_session  FROM spring_session_attributes  INNER JOIN spring_session WHERE spring_session_attributes.session_id= spring_session.session_id and spring_session_attributes.attribute_name=:login",nativeQuery=true)
    public void clearSession(@Param("login") String login);
	

	
	
	
	
	
}
	