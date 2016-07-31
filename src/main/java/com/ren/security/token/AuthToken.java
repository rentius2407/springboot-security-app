/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.security.token;

import com.ren.user.UserDetail;

/**
 *
 * @author rentius
 */
public class AuthToken {

    private UserDetail userDetail;
    private String number;

    public AuthToken() {
    }

    public AuthToken(String number, UserDetail userDetail) {
        this.number = number;
        this.userDetail = userDetail;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }
}
