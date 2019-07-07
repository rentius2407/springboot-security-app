/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.user.group;

import com.ren.category.option.Option;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cp332918
 */
@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Transactional(readOnly = true)
    public Group findById(Long id) {
        return groupRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Option> findOptionByGroupId(Long groupId) {
        return groupRepository.findOptionByGroupId(groupId);
    }

    public Group update(Group group) {
        return groupRepository.update(group);
    }

}
