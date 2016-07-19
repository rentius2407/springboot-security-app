/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.security.authentication;

import java.io.Serializable;

/**
 *
 * @author cp332918
 */
public class AuthenticationCredentials implements Serializable {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean invalid() {
        return username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty();
    }

}
