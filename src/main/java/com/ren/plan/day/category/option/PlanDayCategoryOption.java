/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.plan.day.category.option;

import com.ren.category.option.Option;
import com.ren.plan.day.category.*;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author rentius
 */
@Entity
@Table(name = "plan_day_cat_option")
public class PlanDayCategoryOption implements Serializable {

    @Id
    @GeneratedValue(generator = "pdcoSeq")
    @SequenceGenerator(name = "pdcoSeq", sequenceName = "pdco_seq", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "option_id")
    private Option option;
    @ManyToOne
    @JoinColumn(name = "plan_day_cat_id")
    private PlanDayCategory planDayCategory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public PlanDayCategory getPlanDayCategory() {
        return planDayCategory;
    }

    public void setPlanDayCategory(PlanDayCategory planDayCategory) {
        this.planDayCategory = planDayCategory;
    }

}
