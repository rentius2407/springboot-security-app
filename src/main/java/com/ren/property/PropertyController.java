/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.property;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    PropertyService propertyService;
    private final String LOG_TEMPLATE = "Inside the %s method of " + this.getClass().getCanonicalName();

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Property> getAll() {
        List<Property> properties = propertyService.getAll();

        return properties;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Property get(@PathVariable("id") Long id) {
        return propertyService.get(id);
    }
}
