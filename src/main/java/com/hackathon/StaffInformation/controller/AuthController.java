package com.hackathon.StaffInformation.controller;

import com.hackathon.StaffInformation.exception.AuthException;
import com.hackathon.StaffInformation.model.Actions;
import com.hackathon.StaffInformation.model.Credentials;
import com.hackathon.StaffInformation.model.User;
import com.hackathon.StaffInformation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.*;

@RestController()
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    public static final Long userRoleId = 2L;
    public static final Long adminRoleId = 1L;
    public static Long currentRoleId;
    public static String userSessionId;

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){this.userService = userService;}

    @PostMapping("/login")
    public ResponseEntity<Set<Actions>> getActionsForUser(@RequestBody Credentials credentials) throws AuthException {
        String enteredUsername = credentials.getUsername();
        Optional<User> user = userService.findUserById(enteredUsername);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(8);
        String actualPassword = user.get().getPassword();

        if(user!=null && encoder.matches(credentials.getPassword(), actualPassword)){
            currentRoleId = user.get().getRole().getRoleId();
            return new ResponseEntity<>(userService.getActionsForUser(credentials.getUsername(), credentials.getPassword()), HttpStatus.OK);
        }
        else{
            throw new AuthException("Please enter valid username and password");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createNewUser(@RequestBody User userData) throws AuthException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(8);
        String result = encoder.encode(userData.getPassword());
        userData.setPassword(result);
        String response = userService.createUser(userData);
        userService.setRoleOnSignUp(userRoleId, userData.getUsername());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/adminlogin")
    public void setRoles(@RequestParam String userId, @RequestParam Long roleId) throws AuthException{
        userService.setRole(roleId, userId);
    }
}











