/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.category;

import com.ren.category.option.Option;
import com.ren.category.option.event.OptionCreateEvent;
import java.util.List;
import java.util.Set;
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
    
    @Transactional
    public Set<Option> create(OptionCreateEvent optionCreateEvent) {
        
        Category category = findCategoryById(optionCreateEvent.getCategoryId());
        category.add(optionCreateEvent.getOption());
        category = categoryRespository.update(category);
        
        return category.getOptions();
    }

    @Transactional(readOnly = true)
    public List<Option> findOptionsForCategory(Long categoryId) {
        return categoryRespository.findOptionsForCategory(categoryId);
    }
    
    @Transactional(readOnly = true)
    public Category findCategoryWithOptions(Long categoryId) {
        return categoryRespository.findWithOptions(categoryId);
    }
    
}
