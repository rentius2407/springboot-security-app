/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.user;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rentius
 */
@RestController
@RequestMapping("/user")
public class UserController {
    
    @RequestMapping(method = RequestMethod.GET)
    public List<User> get() {
        return Arrays.asList(new User("Rentius", "Engelbrecht"));
    }
}
