package com.hackathon.StaffInformation.service;

import com.hackathon.StaffInformation.exception.AuthException;
import com.hackathon.StaffInformation.model.Actions;
import com.hackathon.StaffInformation.model.User;

import java.util.*;

public interface UserService {
    Set<Actions> getActionsForUser(String userId, String password);

    String createUser(User user) throws AuthException;

    Optional<User> findUserById(String id);

    void setRoleOnSignUp(Long role, String userId);

    void setRole(Long role, String username) throws AuthException;
}
