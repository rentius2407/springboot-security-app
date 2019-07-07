/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.user;

import com.ren.db.SingleResultNull;
import com.ren.plan.day.Plan;
import java.util.List;
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
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery(User.FIND_BY_USERNAME.QUERY, User.class);
        query.setParameter(User.FIND_BY_USERNAME.PARAM_EMAIL, username.toUpperCase());

        return new SingleResultNull<>(query).get();
    }

    public User create(User user) {
        entityManager.persist(user);
        return user;
    }

    public List<User> findAll() {
        return entityManager.createQuery(User.FIND_ALL.QUERY, User.class)
                .getResultList();
    }
}
