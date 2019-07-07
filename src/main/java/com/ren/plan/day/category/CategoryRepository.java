/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.plan.day.category;

import com.ren.category.option.Option;
import com.ren.db.SingleResultNull;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cp332918
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class CategoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Category> findRootCategories() {
        return entityManager.createQuery(Category.FIND_ALL_ROOT.QUERY, Category.class)
                .getResultList();
    }

    public List<Category> findCategoryByParentId(Long id) {
        return entityManager.createQuery(Category.FIND_ALL_BY_PARENT.QUERY, Category.class)
                .setParameter(Category.FIND_ALL_BY_PARENT.PARAM_PARENT_ID, id)
                .getResultList();
    }

    public Category findById(Long id) {
        return entityManager.find(Category.class, id);
    }

    public Category update(Category category) {
        return entityManager.merge(category);
    }

    public List<Option> findOptions() {
        return entityManager.createQuery(Option.FIND_ALL.QUERY, Option.class)
                .getResultList();
    }

    public Category findWithOptions(Long categoryId) {
        TypedQuery<Category> query = entityManager.createQuery(Category.FIND_WITH_OPTIONS.QUERY, Category.class)
                .setParameter(Category.FIND_WITH_OPTIONS.PARAM_ID, categoryId);

        return new SingleResultNull<>(query).get();
    }
    
    public List<PlanDayCategory> findByPlanDayWithCategory(long planDayId) {

        final String PLAN_DAY_ID = "planDay";

        return entityManager.createQuery("select pdc from PlanDayCategory pdc join fetch pdc.category where pdc.planDay.id = :" + PLAN_DAY_ID, PlanDayCategory.class)
                .setParameter(PLAN_DAY_ID, planDayId)
                .getResultList();
    }


//    public List<Option> findOptionsByCategoryAndGroup(Long categoryId, Long groupId) {
//        TypedQuery<Option> query = entityManager.createQuery(Option.FIND_BY_CATEGORY_ID_AND_GROUP_ID.QUERY, Option.class)
//                .setParameter(Option.FIND_BY_CATEGORY_ID_AND_GROUP_ID.PARAM_CATEGORY_ID, categoryId)
//                .setParameter(Option.FIND_BY_CATEGORY_ID_AND_GROUP_ID.PARAM_GROUP_ID, groupId);
//        
//        return query.getResultList();
//        
//    }

}
