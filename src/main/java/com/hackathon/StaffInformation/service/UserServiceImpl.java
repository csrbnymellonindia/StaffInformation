package com.hackathon.StaffInformation.service;


import com.hackathon.StaffInformation.exception.AuthException;
import com.hackathon.StaffInformation.model.Actions;
import com.hackathon.StaffInformation.model.Role;
import com.hackathon.StaffInformation.model.User;
import com.hackathon.StaffInformation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

import static com.hackathon.StaffInformation.controller.AuthController.adminRoleId;
import static com.hackathon.StaffInformation.controller.AuthController.currentRoleId;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Set<Actions> getActionsForUser(String userId, String password) {
        Optional<User> user = userRepository.findById(userId);
        User person = user.get();
        Role role = person.getRole();
        return role.getActions();
    }

    @Override
    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public void setRoleOnSignUp(Long role, String userId) {
        userRepository.setRoleOnSignup(role, userId);
    }

    @Override
    public void setRole(Long role, String userId) throws AuthException {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (currentRoleId == adminRoleId) {
                if (role >= user.getRole().getRoleId()) {
                    throw new AuthException("This user is already admin");
                } else {
                    userRepository.setRoleForUser(role, userId);
                }
            } else {
                throw new AuthException("You are not authorized for this action");
            }
        }
        else{
            throw new AuthException("User with comit if " + userId + " does not exist");
        }
    }

    @Override
    public String createUser(User user) throws AuthException {
        if (userRepository.existsById(user.getUsername())) {
            throw new AuthException("User already exists. Try logging in");
        }

        User newUser = userRepository.save(user);

        return "SignUp successful";
    }
}
