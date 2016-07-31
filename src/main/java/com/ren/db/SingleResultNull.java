/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.db;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 *
 * @author rentius
 */
public class SingleResultNull<T> {

    private final TypedQuery<T> query;

    public SingleResultNull(TypedQuery<T> query) {
        this.query = query;
    }

    public T get() {
        try {
            return query.getSingleResult();
        } catch (NoResultException | EmptyResultDataAccessException e) {
            return null;
        }
    }
}
