/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.user;

import com.ren.api.MappingApi;
import com.ren.user.create.NewUser;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
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
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDetail> create(@RequestBody NewUser newUser) {
        User user = userService.create(newUser);

        UserDetail userDetail = UserDetail.from(user);
        return new ResponseEntity<>(userDetail, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDetail>> findAll() {

        List<UserDetail> userDetails = new ArrayList<>();

        List<User> users = userService.findAll();
        users.stream().forEach((user) -> {
            userDetails.add(UserDetail.from(user));
        });

        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

}
