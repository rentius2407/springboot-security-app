/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.user.group;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rentius
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class GroupRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Group findById(Long id) {
        return entityManager.find(Group.class, id);
    }
}
