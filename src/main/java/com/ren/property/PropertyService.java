/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.property;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PropertyService {

    @Autowired
    PropertyRepository propertyRepository;

    @Transactional(readOnly = true)
    public List<Property> getAll() {
        return propertyRepository.getAll();
    }

    @Transactional(readOnly = true)
    public Property get(Long id) {
        return propertyRepository.get(id);
    }

}
