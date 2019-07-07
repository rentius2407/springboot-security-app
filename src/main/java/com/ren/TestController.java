/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren;

import com.ren.api.MappingApi;
import com.ren.plan.day.Plan;
import com.ren.plan.day.PlanDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rentius
 */
@RestController
@RequestMapping(MappingApi.IMAGE)
public class TestController {

    @Autowired
    PlanDayService planService;

    @RequestMapping(value = "/{planId}/image", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("planId") Long planId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());

        Plan plan = planService.findPlan(planId);
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(plan.getImage(), headers, HttpStatus.OK);
        return responseEntity;
    }

}
