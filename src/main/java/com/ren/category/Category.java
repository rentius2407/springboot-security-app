/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.category;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    public static class FIND_ALL_ROOT {

        public final static String QUERY = "select c from Category c where c.parentCategory is null";
    }
    public static class FIND_ALL_BY_PARENT {

        public final static String PARAM_PARENT_ID = "id";
        public final static String QUERY = "select c from Category c JOIN c.parentCategory pc where pc.id = :" + PARAM_PARENT_ID;
    }

}
