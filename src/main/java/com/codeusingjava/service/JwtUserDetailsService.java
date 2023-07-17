package com.codeusingjava.service;

import com.codeusingjava.custom.CustomUserDetails;
import com.codeusingjava.model.StudentModel;
import com.codeusingjava.model.UserDao;
import com.codeusingjava.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;





@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDao user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		return new CustomUserDetails(user.getUsername(),user.getPassword(),user.getType());
	}

	public UserDao save(UserDao user) {
		UserDao newUser = new UserDao();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setType(user.getType());
		return userDao.save(newUser);
	}

	public String getUserType(String username){
		UserDao user=userDao.findByUsername(username);
		return user.getType();
	}

    public List<UserDao> findAllUsers() {
        return userDao.findAll();
    }

}