/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.user.role;

import com.ren.db.SingleResultNull;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rentius
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class RoleRepository {
    
    @PersistenceContext
    private EntityManager entityManager;

    public Role findByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery(Role.FIND_BY_NAME.QUERY, Role.class);
        query.setParameter(Role.FIND_BY_NAME.PARAM_NAME, name);

        return new SingleResultNull<>(query).get();
    }    
    
}
