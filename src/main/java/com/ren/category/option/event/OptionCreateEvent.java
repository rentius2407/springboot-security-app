/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.category.option.event;

import com.ren.category.option.Option;

/**
 *
 * @author cp332918
 */
public class OptionCreateEvent {

    private final long categoryId;
    private final Option option;

    public OptionCreateEvent(long categoryId, Option option) {
        this.categoryId = categoryId;
        this.option = option;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public Option getOption() {
        return option;
    }
    
    public String getOptionName() {
        return this.option.getName();
    }
}
