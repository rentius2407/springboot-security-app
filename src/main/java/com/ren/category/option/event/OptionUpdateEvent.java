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
public class OptionUpdateEvent {

    private final long categoryId;
    private final Option option;

    public OptionUpdateEvent(long categoryId, Option option) {
        this.option = option;
        this.categoryId = categoryId;
    }

    public Option getOption() {
        return option;
    }

    public long getCategoryId() {
        return categoryId;
    }

}
