/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.user;

import com.ren.plan.day.Plan;
import com.ren.security.authentication.AuthenticationCredentials;
import com.ren.security.authentication.AuthenticationException;
import com.ren.user.create.NewUser;
import com.ren.user.group.Group;
import com.ren.user.group.GroupService;
import com.ren.user.role.Role;
import com.ren.user.role.RoleService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cp332918
 */
@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GroupService groupService;
    @Autowired
    RoleService roleService;
    private final String DEFAULT_PASSWORD_APPEND = "jq_";
    private final String INVALID_USERNAME_PASSWORD = "Invalid username or password";

    public void register() {

        String password = "password";
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println("encodedPassword = " + encodedPassword);

        System.out.println("Encode again and then match the passwords");
        encodedPassword = passwordEncoder.encode(password);
        boolean matches = passwordEncoder.matches(password, encodedPassword);
        System.out.println("matches = " + matches);
    }

    @Transactional(readOnly = true)
    public User authenticate(AuthenticationCredentials credentials) {
        User user = userRepository.findByUsername(credentials.getUsername());
        if (user == null) {
            throw new AuthenticationException(INVALID_USERNAME_PASSWORD);
        }

        boolean matches = passwordEncoder.matches(credentials.getPassword(), user.getPassword());
        if (!matches) {
            throw new AuthenticationException(INVALID_USERNAME_PASSWORD);
        }

        return user;
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public User create(NewUser newUser) {

        User user = new User();
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());

        String email = newUser.getEmail();
        user.setEmail(email);

        String encodedPassword = passwordEncoder.encode(DEFAULT_PASSWORD_APPEND + email);
        user.setPassword(encodedPassword);

        Group group = groupService.findById(newUser.getGroupId());
        user.setGroup(group);

        Role role = roleService.findByUsername("USER");
        user.setRole(role);

        user = userRepository.create(user);
        return user;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

}
