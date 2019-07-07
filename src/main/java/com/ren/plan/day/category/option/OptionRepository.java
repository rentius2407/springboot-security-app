/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.plan.day.category.option;

import com.ren.plan.day.category.*;
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
public class OptionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    
    public List<PlanDayCategoryOption> findByPlanDayCategoryWithOption(long planDayCategoryId) {

        final String PLAN_DAY_CATEGORY_ID = "planDayCategory";

        return entityManager.createQuery("select pdco from PlanDayCategoryOption pdco join fetch pdco.option where pdco.planDayCategory.id = :" + PLAN_DAY_CATEGORY_ID, PlanDayCategoryOption.class)
                .setParameter(PLAN_DAY_CATEGORY_ID, planDayCategoryId)
                .getResultList();
    }

}
