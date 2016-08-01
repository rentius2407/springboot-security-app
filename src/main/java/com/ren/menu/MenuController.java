/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.menu;

import com.ren.api.MappingApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cp332918
 */
@RequestMapping(MappingApi.MENU)
@RestController
public class MenuController {

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Menu> all(@PathVariable("userId") Long userId) {

        Menu menu = new Menu();
        menu.setId(1L);

        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

}
