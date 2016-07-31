/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.user;

import com.ren.api.MappingApi;
import com.ren.user.role.Role;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rentius
 */
@RestController
@RequestMapping(MappingApi.USER)
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public List<User> get() {
        User user = new User();
        user.setEmail("Rentius@gmail.com");
        user.setRole(new Role("ADMIN"));

        return Arrays.asList(user);
    }

    @RequestMapping(value = MappingApi.REGISTER, method = RequestMethod.POST)
    public ResponseEntity<String> register() {

        userService.register();

        return new ResponseEntity<>("Working", HttpStatus.OK);
    }

}
