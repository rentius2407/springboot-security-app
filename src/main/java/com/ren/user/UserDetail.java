/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.user;

/**
 *
 * @author rentius
 */
public class UserDetail {

    private String firstName;
    private String lastName;
    private String role;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static UserDetail from(User user) {
        UserDetail detail = new UserDetail();
        detail.setFirstName(user.getFirstName());
        detail.setLastName(user.getLastName());
        detail.setRole(user.getRoleName());

        return detail;
    }
}
