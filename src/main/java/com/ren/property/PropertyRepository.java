/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.property;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class PropertyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Property> getAll() {

        TypedQuery<Property> query = entityManager.createQuery("select p from Property p", Property.class);
        List<Property> properties = query.getResultList();
        return properties;
    }

    public Property get(Long id) {
        return entityManager.find(Property.class, id);
    }

}
