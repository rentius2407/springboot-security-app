/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.user.group;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ren.category.option.Option;
import com.ren.user.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author rentius
 */
@Entity
@Table(name = "app_group")
public class Group implements Serializable {

    @Id
    @GeneratedValue(generator = "appGroupSeq")
    @SequenceGenerator(name = "appGroupSeq", sequenceName = "app_group_seq", allocationSize = 1)
    private Long id;
    @Column(name = "name")
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<User> users;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "app_group_option", joinColumns = @JoinColumn(name = "app_group_id"), inverseJoinColumns = @JoinColumn(name = "option_id"))
    private List<Option> options = new ArrayList<>();

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

}
