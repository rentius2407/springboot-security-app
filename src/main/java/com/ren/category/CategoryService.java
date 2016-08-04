/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.category;

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
}
