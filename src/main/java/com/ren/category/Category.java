/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ren.category.option.Option;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author cp332918
 */
@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(generator = "categorySeq")
    @SequenceGenerator(name = "categorySeq", sequenceName = "category_seq", allocationSize = 1)
    private Long id;
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "parent_id")
    @ManyToOne
    private Category parentCategory;
    @Column(name = "view_state")
    private String viewState;
    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<Option> options = new HashSet<>();

    public Category() {
    }

    public Category(String name) {
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

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public String getViewState() {
        return viewState;
    }

    public void setViewState(String viewState) {
        this.viewState = viewState;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }

    public void add(Option option) {
        option.setCategory(this);
        getOptions().add(option);
    }

    public void update(Option option) {
       getOption(option.getId()).updateWith(option);
    }

    public Option getOption(Long optionId) {
        for (Option option : getOptions()) {
            if (optionId.equals(option.getId())) {
                return option;
            }
        }
        throw new IllegalArgumentException("Option with id not found: " + optionId);
    }

    public static class FIND_ALL_ROOT {

        public final static String QUERY = "select c from Category c where c.parentCategory is null";
    }

    public static class FIND_ALL_BY_PARENT {

        public final static String PARAM_PARENT_ID = "id";
        public final static String QUERY = "select c from Category c JOIN c.parentCategory pc where pc.id = :" + PARAM_PARENT_ID;
    }

    public static class FIND_WITH_OPTIONS {

        public final static String PARAM_ID = "id";
        public final static String QUERY = "select c from Category c LEFT JOIN FETCH c.options where c.id = :" + PARAM_ID;
    }
    
    public static class FIND_ASSIGNED_GROUPS {
        public static final String PARAM_CATEGORY_ID = "id";
        public static final String QUERY = "select g from Groups g JOIN g.options o JOIN o.category c where c.id = :" + PARAM_CATEGORY_ID;
    }    

}
