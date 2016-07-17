/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.security;

import com.ren.api.MappingApi;
import com.ren.security.token.AuthToken;
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

    @RequestMapping(method = RequestMethod.POST)
    public AuthToken authenticate() {
        return new AuthToken("1234", "Rentius");
    }

}
