/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.plan.day;

import com.ren.user.UserPlan;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cp332918
 */
@Service
public class PlanDayService {

    @Autowired
    private PlanRepository planRepository;

    @Transactional(readOnly = true)
    public List<Day> findDaysForPlan(long planId) {

        List<Day> days = new ArrayList<>();

        List<PlanDay> planDays = planRepository.findByPlanWithDays(planId);
        for (PlanDay planDay : planDays) {
            days.add(planDay.getDay());
        }

        return days;
    }

    @Transactional(readOnly = true)
    public List<Plan> findPlansForUser(long userId) {
        List<UserPlan> userPlans = planRepository.findUserPlanForUser(userId);

        ArrayList<Plan> result = new ArrayList<>();

        if (!userPlans.isEmpty()) {
            for (UserPlan userPlan : userPlans) {
                result.add(userPlan.getPlan());
            }
        }

        return result;
    }

    @Transactional
    public Plan create() throws IOException {
        Plan plan = new Plan();
        plan.setCode("Test");
        plan.setName("Test");
        Path path = Paths.get("C:\\images\\Banner.jpg");
        byte[] bytes = Files.readAllBytes(path);
        plan.setImage(bytes);

        planRepository.create(plan);
        return plan;
    }

    @Transactional
    public Plan findPlan(Long planId) {
        return planRepository.findPlan(planId);
    }

}
