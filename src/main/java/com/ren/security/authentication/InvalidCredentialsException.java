/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.security.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author cp332918
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid credentials presented")
public class InvalidCredentialsException extends RuntimeException {

}
