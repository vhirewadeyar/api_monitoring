package com.mercurit.apialerts.security;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mercurit.apialerts.domain.Users;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class JWTUtil
{

	
	
	private Key key=init();
	public Key init() {
		KeyGenerator keyGen = null;
		try {
			 keyGen = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SecureRandom secureRandom=new SecureRandom();
		keyGen.init(secureRandom);
		return this.key=keyGen.generateKey();
	}
	
	public String extractUsername(String token){
		return extractClaim(token,Claims::getSubject);
	}
	
	public Date extractExpiration(String token){
		return extractClaim(token,Claims::getExpiration);
	}
	
	public <T> T extractClaim(String token,Function<Claims,T> claimsResolver) {
		final Claims claims=extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	public Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	}
	
	public Boolean isTokenExpired(String token) {
		
		return extractExpiration(token).before(new Date());
		
	}
	
	public String generateToken(Users users){
		Map<String,Object> claims=new HashMap<>();
		return createToken(claims,users.getUserName());
	}
	
	public String createToken(Map<String,Object> claims,String subject) {
		String token= Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*10))
				.signWith(SignatureAlgorithm.HS512, key).compact();
		return token;
	}
	
	public boolean validateToken(String token,UserDetails userDetails) {
		final String username=extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
