/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.plan.day.category;

import com.ren.plan.day.PlanDay;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "plan_day_cat")
public class PlanDayCategory implements Serializable {

    @Id
    @GeneratedValue(generator = "panDayCatSeq")
    @SequenceGenerator(name = "panDayCatSeq", sequenceName = "plan_day_cat_seq", allocationSize = 1)
    private Long id;
    @JoinColumn(name = "plan_day_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PlanDay planDay;
    @JoinColumn(name = "category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlanDay getPlanDay() {
        return planDay;
    }

    public void setPlanDay(PlanDay planDay) {
        this.planDay = planDay;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
