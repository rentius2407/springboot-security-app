/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author cp332918
 */
@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    public void register() {

        String password = "password";
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println("encodedPassword = " + encodedPassword);

        System.out.println("Encode again and then match the passwords");
        encodedPassword = passwordEncoder.encode(password);
        boolean matches = passwordEncoder.matches(password, encodedPassword);
        System.out.println("matches = " + matches);
    }
}
