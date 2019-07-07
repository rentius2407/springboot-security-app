/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.plan.day;

import com.ren.user.UserPlan;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cp332918
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class PlanRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Plan> findPlansForUser(long userId) {
        return entityManager.createQuery("select p from Plan p", Plan.class)
                .getResultList();
    }

    public List<PlanDay> findAll() {
        return entityManager.createQuery("select pd from PlanDay pd", PlanDay.class)
                .getResultList();
    }

    public List<PlanDay> findByPlanWithDays(long planId) {
        return entityManager.createQuery(PlanDay.FIND_BY_PLAN_ID_WITH_DAYS.QUERY, PlanDay.class)
                .setParameter(PlanDay.FIND_BY_PLAN_ID_WITH_DAYS.PARAM_PLAN_ID, planId)
                .getResultList();
    }

    public void create(Plan plan) {
        entityManager.persist(plan);
    }

    public Plan findPlan(Long planId) {
        return entityManager.find(Plan.class, planId);
    }
    
    public List<UserPlan> findUserPlanForUser(Long userId) {

        final String PARAM = "id";
        final String QUERY = "select up from UserPlan up join fetch up.plan where up.user.id = :" + PARAM;

        return entityManager.createQuery(QUERY, UserPlan.class)
                .setParameter(PARAM, userId)
                .getResultList();
    }    

}
