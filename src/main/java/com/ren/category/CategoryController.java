/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.category;

import com.ren.api.MappingApi;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cp332918
 */
@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(MappingApi.CATEGORY)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Category>> findRootCategories() {

        List<Category> rootCategories = categoryService.findRootCategories();
        return new ResponseEntity<>(rootCategories, HttpStatus.OK);
    }

    @RequestMapping(value = "/parent/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> findCategoryByParentId(@PathVariable("id") Long parentId) {

        List<Category> categories = categoryService.findCategoryByParentId(parentId);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> findCategoryById(@PathVariable("id") Long id) {
        System.out.println("id = " + id);
        Category category = categoryService.findCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

}
