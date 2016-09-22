/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.category;

import com.ren.api.MappingApi;
import com.ren.category.option.Option;
import com.ren.category.option.event.OptionCreateEvent;
import com.ren.category.option.event.OptionUpdateEvent;
import com.ren.user.group.Group;
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
@RequestMapping(MappingApi.CATEGORY)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Category>> findRootCategories() {

        List<Category> rootCategories = categoryService.findRootCategories();
        return new ResponseEntity<>(rootCategories, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @RequestMapping(value = "/parent/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> findCategoryByParentId(@PathVariable("id") Long parentId) {

        List<Category> categories = categoryService.findCategoryByParentId(parentId);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> findCategoryById(@PathVariable("id") Long id) {
        Category category = categoryService.findCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Set<Option>> createOption(@PathVariable("id") long categoryId, @RequestBody Option option) {
        Set<Option> options = categoryService.create(new OptionCreateEvent(categoryId, option));
        return new ResponseEntity<>(options, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @RequestMapping(value = "/{id}/option", method = RequestMethod.GET)
    public ResponseEntity<OptionsDto> findOptionsForCategory(@PathVariable("id") Long categoryId) {
        Category category = categoryService.findCategoryWithOptions(categoryId);
        OptionsDto optionsDto = new OptionsDto(category, category.getOptions());
        return new ResponseEntity<>(optionsDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/{categoryId}/option/{optionId}", method = RequestMethod.GET)
    public ResponseEntity<Option> findOptionById(@PathVariable("categoryId") Long categoryId, @PathVariable("optionId") Long optionId) {
        Category category = categoryService.findCategoryWithOptions(categoryId);
        Option option = category.getOption(optionId);
        return new ResponseEntity<>(option, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/{categoryId}/option", method = RequestMethod.PUT)
    public ResponseEntity<Set<Option>> createOption(@PathVariable("categoryId") Long categoryId, @RequestBody Option option) {
        Set<Option> options = categoryService.update(new OptionUpdateEvent(categoryId, option));
        return new ResponseEntity<>(options, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/{categoryId}/group/{groupId}/option", method = RequestMethod.GET)
    public ResponseEntity<List<Option>> findOptionByCategoryAndGroup(@PathVariable("categoryId") Long categoryId, @PathVariable("groupId") Long groupId) {

        List<Option> options = categoryService.findOptionByCategoryAndGroup(categoryId, groupId);
        return new ResponseEntity<>(options, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/{categoryId}/group", method = RequestMethod.GET)
    public ResponseEntity<List<Group>> assignedGroup(@PathVariable("categoryId") Long categoryId) {
        List<Group> options = categoryService.assignedGroups(categoryId);
        return new ResponseEntity<>(options, HttpStatus.OK);
    }
}
