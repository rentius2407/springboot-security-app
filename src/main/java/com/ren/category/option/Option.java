/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.category.option;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    @Column(name = "title")
    private String title;
    @Column(name = "ingredient")
    private String ingredient;
    @Column(name = "direction")
    private String direction;
    @Column(name = "nutrition")
    private String nutrition;
//    @JsonIgnore
//    @ManyToMany(mappedBy = "options", fetch = FetchType.LAZY)
//    private List<Group> groups = new ArrayList<>();

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getNutrition() {
        return nutrition;
    }

    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

//    public List<Group> getGroups() {
//        return groups;
//    }
//
//    public void setGroups(List<Group> groups) {
//        this.groups = groups;
//    }
//    public void updateWith(Option option) {
//        setName(option.getName());
//        setContent(option.getContent());
//    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.title);
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
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return true;
    }

//    public static class FIND_BY_CATEGORY_ID {
//
//        public final static String PARAM_CATEGORY_ID = "id";
//        public final static String QUERY = "select o from Option o JOIN o.category c where c.id = :" + PARAM_CATEGORY_ID;
//    }
    public static class FIND_ALL {

        public final static String QUERY = "select o from Option o";
    }
//
//    public static class FIND_BY_CATEGORY_ID_AND_GROUP_ID {
//
//        public final static String PARAM_CATEGORY_ID = "category_id";
//        public final static String PARAM_GROUP_ID = "group_id";
//        public final static String QUERY = "select o from Option o JOIN o.category c JOIN o.groups g where c.id = :" + PARAM_CATEGORY_ID
//                + " AND g.id = :" + PARAM_GROUP_ID;
//    }
}
