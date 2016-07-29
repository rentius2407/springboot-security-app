/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.security.token.claim;

import org.joda.time.DateTime;

/**
 *
 * @author cp332918
 */
public class ExpireDate {

    private DateTime date;

    public ExpireDate now() {
        return set(new DateTime());
    }

    public ExpireDate set(DateTime date) {
        this.date = date;
        return this;
    }

    public ExpireDate time(long time) {
        date = new DateTime(time);
        return this;
    }

    public ExpireDate plusDays(int day) {
        date = date.plusDays(day);
        return this;
    }

    public long getMillis() {
        return date.getMillis();
    }

    public boolean after(DateTime date) {
        return date.isAfter(date.getMillis());
    }
}
