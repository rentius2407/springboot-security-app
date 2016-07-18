/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.security;

import com.ren.api.MappingApi;
import com.ren.security.token.util.JwtUtil;
import com.ren.user.User;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String authenticate() {

        User user = new User();
        user.setUsername("Rentius");
        user.setRole("ADMIN");
        user.setId(20L);

        return jwtUtil.generateToken(user);
    }

}
