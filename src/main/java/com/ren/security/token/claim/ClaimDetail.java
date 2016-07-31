/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.security.token.claim;

import com.ren.user.User;
import org.joda.time.DateTime;

/**
 *
 * @author cp332918
 */
public class ClaimDetail {

    private User user;
    private ExpireDate expireDate;

    public ClaimDetail(User user, ExpireDate expireDate) {
        this.user = user;
        this.expireDate = expireDate;
    }

    public ClaimDetail(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ExpireDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(ExpireDate expireDate) {
        this.expireDate = expireDate;
    }

    public long getExpireTime() {
        return this.expireDate.getMillis();
    }

    public String getUsername() {
        return getUser().getUsername();
    }

    public Long getUserId() {
        return getUser().getId();
    }

    public String getRole() {
        return getUser().getRole();
    }

    public boolean tokenExpired() {
        if (getExpireDate() == null) {
            return true;
        }

        return getExpireDate().after(DateTime.now());
    }

}
