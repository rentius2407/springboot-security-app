/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.plan.day;

import com.ren.api.MappingApi;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rentius
 */
@RestController
@RequestMapping(MappingApi.PLAN)
public class PlanController {

    @Autowired
    PlanDayService planDayService;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<Plan>> find(@PathVariable("userId") Long userId) {

        List<Plan> plans = planDayService.findPlansForUser(userId);
        return new ResponseEntity<>(plans, HttpStatus.OK);
    }
    
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @RequestMapping(value = "/{planId}/day", method = RequestMethod.GET)
    public ResponseEntity<List<Day>> findDaysForPlan(@PathVariable("planId") Long planId) {

        List<Day> days = planDayService.findDaysForPlan(planId);
        return new ResponseEntity<>(days, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Plan> create() {

        try {
            Plan plan = planDayService.create();
            return new ResponseEntity<>(plan, HttpStatus.OK);
        } catch (IOException ioe) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
