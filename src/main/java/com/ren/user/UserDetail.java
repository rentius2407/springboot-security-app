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

    private Long id;
    private String firstName;
    private String lastName;
    private String role;
    private Long groupId;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public static UserDetail from(User user) {
        UserDetail detail = new UserDetail();
        detail.setFirstName(user.getFirstName());
        detail.setLastName(user.getLastName());
        detail.setRole(user.getRoleName());
        detail.setId(user.getId());
        detail.setGroupId(user.getGroupId());

        return detail;
    }
}
