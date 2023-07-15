package com.codeusingjava.custom;

import com.codeusingjava.model.UserDao;
import com.codeusingjava.repository.UserRepository;

import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.springframework.util.Assert;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String type;
    private String password;

    public CustomUserDetails(String username, String password, String type) {
        this.username = username;
        this.type = type;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return an empty collection since we are not implementing role-based authorization
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        // Assume the account never expires
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Assume the account is never locked
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Assume the credentials never expire
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Assume the user is always enabled
        return true;
    }
}
