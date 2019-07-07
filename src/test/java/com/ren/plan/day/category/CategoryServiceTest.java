/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.plan.day.category;

import java.util.List;
import org.junit.Ignore;
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
public class CategoryServiceTest {
    
    @Autowired
    CategoryService categoryService;
    
    @Ignore
    @Test
    public void findByPlanDayWithCategory() {
        
        List<PlanDayCategory> categories = categoryService.findByPlanDayWithCategory(2);
        System.out.println(categories.size());
        categories.stream().forEach((planDayCategory) -> {
            System.out.println(planDayCategory.getCategory().getId());
        });
        
    }
}
