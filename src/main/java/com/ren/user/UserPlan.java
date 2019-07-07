/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.user;

import com.ren.plan.day.*;
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
@Table(name = "app_user_plan")
public class UserPlan implements Serializable {

    @Id
    @GeneratedValue(generator = "userPlanSeq")
    @SequenceGenerator(name = "userPlanSeq", sequenceName = "app_user_plan_seq", allocationSize = 1)
    private Long id;
    @JoinColumn(name = "plan_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Plan plan;
    @JoinColumn(name = "app_user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
