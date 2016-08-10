/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.category;

import com.ren.api.MappingApi;
import com.ren.category.option.Option;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
        Category category = categoryService.findCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Set<Option>> createOption(@PathVariable("id") Long categoryId, @RequestBody Option option) {
        Set<Option> options = categoryService.create(categoryId, option);
        return new ResponseEntity<>(options, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/option", method = RequestMethod.GET)
    public ResponseEntity<OptionsDto> findOptionsForCategory(@PathVariable("id") Long categoryId) {
        Category category = categoryService.findCategoryWithOptions(categoryId);
        OptionsDto optionsDto = new OptionsDto(category, category.getOptions());
        return new ResponseEntity<>(optionsDto, HttpStatus.OK);
    }

}