package com.codeusingjava.controller;

import com.codeusingjava.config.JwtTokenUtil;
import com.codeusingjava.custom.CustomUserDetails;
import com.codeusingjava.model.JwtRequest;
import com.codeusingjava.model.JwtResponse;
import com.codeusingjava.model.UserDao;
import com.codeusingjava.service.JwtUserDetailsService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class JwtController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final CustomUserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String role = userDetailsService.getUserType(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		Map<String, Object> responseData = new HashMap<>();
    	responseData.put("token", token);
    	responseData.put("userType",role);
    
    return ResponseEntity.ok(responseData);}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDao user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	@RequestMapping(value = "/admin_authentication", method = RequestMethod.POST)
	public boolean adminAuthentication(@RequestBody JwtRequest authenticationRequest) throws Exception {
		
		if(authenticationRequest.getType().compareTo("admin")==0){
			return true;
		}
		else return false;


	}
	@RequestMapping(value = "/teacher_authentication", method = RequestMethod.POST)
	public boolean teacherAuthentication(@RequestBody JwtRequest authenticationRequest) throws Exception {

		if(authenticationRequest.getType().compareTo("teacher")==0){
			return true;
		}
		else return false;


	}
}
