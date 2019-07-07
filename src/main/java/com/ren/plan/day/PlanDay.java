/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.plan.day;

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
@Table(name = "plan_day")
public class PlanDay implements Serializable {

    @Id
    @GeneratedValue(generator = "panDaySeq")
    @SequenceGenerator(name = "panDaySeq", sequenceName = "pan_day_seq", allocationSize = 1)
    private Long id;
    @JoinColumn(name = "plan_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Plan plan;
    @JoinColumn(name = "day_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Day day;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
    
    public static class FIND_BY_PLAN_ID_WITH_DAYS {

        public final static String PARAM_PLAN_ID = "id";
        public final static String QUERY = "select pd from PlanDay pd join fetch pd.day where pd.plan.id = :" + PARAM_PLAN_ID;
    }

}
