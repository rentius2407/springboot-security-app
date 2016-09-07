/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.user.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rentius
 */
@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Role findByUsername(String name) {
        return roleRepository.findByName(name);
    }

}
