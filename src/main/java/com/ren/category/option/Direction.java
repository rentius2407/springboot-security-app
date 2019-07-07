/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.category.option;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author cp332918
 */
//@Entity
//@Table(name = "direction")
public class Direction implements Serializable {

//    @Id
//    @GeneratedValue(generator = "directionSeq")
//    @SequenceGenerator(name = "directionSeq", sequenceName = "direction_seq", allocationSize = 1)
    private Long id;
//    @Column(name = "value")
    private String value;

    public Direction() {
    }
    
    public Direction(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.value);
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
        final Direction other = (Direction) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }
}
