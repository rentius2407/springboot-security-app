/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.security.token;

/**
 *
 * @author rentius
 */
public class AuthToken {

    private String number;
    private String username;

    public AuthToken(String number, String username) {
        this.number = number;
        this.username = username;
    }

    public AuthToken() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
