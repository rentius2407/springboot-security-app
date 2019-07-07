/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.category;

import com.ren.plan.day.category.Category;
import com.ren.category.option.Option;
import java.util.Set;

/**
 *
 * @author cp332918
 */
public class OptionsDto {

    private Category category;
    private Set<Option> options;

    public OptionsDto(Category category, Set<Option> options) {
        this.category = category;
        this.options = options;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }

}
