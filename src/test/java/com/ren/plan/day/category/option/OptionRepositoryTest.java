/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.plan.day.category.option;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author rentius
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OptionRepositoryTest {

    @Autowired
    OptionService optionService;

    @Test
    public void findByPlanDayCategoryWithOption() {
        List<PlanDayCategoryOption> options = optionService.findByPlanDayCategoryWithOption(3);
        System.out.println(options.size());
        options.forEach((option) -> {
            System.out.println(option.getOption().getName());
        });
    }

}
