/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.category;

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

    public List<Option> findOptionsForCategory(Long categoryId) {
        return entityManager.createQuery(Option.FIND_BY_CATEGORY_ID.QUERY, Option.class)
                .setParameter(Option.FIND_BY_CATEGORY_ID.PARAM_CATEGORY_ID, categoryId)
                .getResultList();
    }

    public Category findWithOptions(Long categoryId) {
        TypedQuery<Category> query = entityManager.createQuery(Category.FIND_WITH_OPTIONS.QUERY, Category.class)
                .setParameter(Category.FIND_WITH_OPTIONS.PARAM_ID, categoryId);

        return new SingleResultNull<>(query).get();
    }

}
