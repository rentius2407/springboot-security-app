/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.category.option;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ren.category.Category;
import com.ren.user.group.Group;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author cp332918
 */
@Entity
@Table(name = "option")
public class Option implements Serializable {

    @Id
    @GeneratedValue(generator = "optionSeq")
    @SequenceGenerator(name = "optionSeq", sequenceName = "option_seq", allocationSize = 1)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "content")
    private String content;
    @JoinColumn(name = "category_id")
    @ManyToOne
    private Category category;
    @JsonIgnore
    @ManyToMany(mappedBy = "options", fetch = FetchType.LAZY)
    private List<Group> groups = new ArrayList<>();

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void updateWith(Option option) {
        setName(option.getName());
        setContent(option.getContent());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.content);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Option other = (Option) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        return true;
    }

    public static class FIND_BY_CATEGORY_ID {

        public final static String PARAM_CATEGORY_ID = "id";
        public final static String QUERY = "select o from Option o JOIN o.category c where c.id = :" + PARAM_CATEGORY_ID;
    }

    public static class FIND_BY_CATEGORY_ID_AND_GROUP_ID {

        public final static String PARAM_CATEGORY_ID = "id";
        public final static String QUERY = "select o from Option o JOIN o.category c where c.id = :" + PARAM_CATEGORY_ID;
    }

}
