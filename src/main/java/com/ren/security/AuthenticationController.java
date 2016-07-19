/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.security;

import com.ren.api.MappingApi;
import com.ren.security.authentication.AuthenticationCredentials;
import com.ren.security.authentication.InvalidCredentialsException;
import com.ren.security.token.util.JwtUtil;
import com.ren.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rentius
 */
@RestController
@RequestMapping(MappingApi.AUTH)
public class AuthenticationController {

    @Autowired
    JwtUtil jwtUtil;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationCredentials credentials) {

        if (credentials == null || credentials.invalid()) {
            throw new InvalidCredentialsException();
        }

        User user = new User();
        user.setUsername(credentials.getUsername());
        user.setRole("ADMIN");
        user.setId(20L);
        
        String encryptedToken = jwtUtil.generateToken(user);

        return new ResponseEntity<>(encryptedToken, HttpStatus.OK);
    }

}
