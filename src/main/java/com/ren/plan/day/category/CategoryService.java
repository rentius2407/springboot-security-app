/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.plan.day.category;

import com.ren.category.option.Option;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cp332918
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRespository;

    @Transactional(readOnly = true)
    public List<Category> findRootCategories() {
        return categoryRespository.findRootCategories();
    }

    @Transactional(readOnly = true)
    public List<Category> findCategoryByParentId(Long id) {
        return categoryRespository.findCategoryByParentId(id);
    }

    @Transactional(readOnly = true)
    public Category findCategoryById(Long id) {
        return categoryRespository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<Option> findOptions() {
        return categoryRespository.findOptions();
    }
    
    @Transactional(readOnly = true)
    public List<PlanDayCategory> findByPlanDayWithCategory(long planDayId) {
        return categoryRespository.findByPlanDayWithCategory(planDayId);
    }
    
//    @Transactional
//    public Set<Option> create(OptionCreateEvent optionCreateEvent) {
//        
//        Category category = findCategoryById(optionCreateEvent.getCategoryId());
//        category.add(optionCreateEvent.getOption());
//        category = categoryRespository.update(category);
//        
//        return category.getOptions();
//    }
    
//    @Transactional
//    public Set<Option> update(OptionUpdateEvent optionUpdateEvent) {
//        
//        Category category = findCategoryWithOptions(optionUpdateEvent.getCategoryId());
//        category.update(optionUpdateEvent.getOption());
//        category = categoryRespository.update(category);
//        
//        return category.getOptions();
//    }

//    @Transactional(readOnly = true)
//    public List<Option> findOptionsForCategory(Long categoryId) {
//        return categoryRespository.findOptionsForCategory(categoryId);
//    }
//    
    @Transactional(readOnly = true)
    public Category findCategoryWithOptions(Long categoryId) {
        return categoryRespository.findWithOptions(categoryId);
    }

//    @Transactional(readOnly = true)
//    public List<Option> findOptionByCategoryAndGroup(Long categoryId, Long groupId) {
//        return categoryRespository.findOptionsByCategoryAndGroup(categoryId, groupId);
//    }
    
}
