/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ren.user.group.Group;
import com.ren.user.role.Role;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author rentius
 */
@Entity
@Table(name = "app_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "appUserSeq")
    @SequenceGenerator(name = "appUserSeq", sequenceName = "app_user_seq", allocationSize = 1)
    private Long id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "app_user_group", joinColumns = @JoinColumn(name = "app_user_id"), inverseJoinColumns = @JoinColumn(name = "app_group_id"))
    private Group group;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getRoleName() {
        return getRole().getName();
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean invalidId(Long userId) {
        return !getId().equals(userId);
    }

    public Long getGroupId() {

        if (!hasRole(Role.INSTANCE.ADMIN)) {
            return getGroup().getId();
        }

        return null;
    }

    public boolean hasRole(Role.INSTANCE roleInstance) {
        return getRole().getName().equals(roleInstance.toString());
    }

    public static class FIND_BY_USERNAME {

        public final static String PARAM_EMAIL = "email";
        public final static String QUERY = "select u from User u where upper(u.email) = :" + PARAM_EMAIL;
    }

}
