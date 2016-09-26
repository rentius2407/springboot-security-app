/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.user.group;

import com.ren.category.option.Option;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rentius
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class GroupRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Group findById(Long id) {
        return entityManager.find(Group.class, id);
    }

    public List<Group> findAll() {
        return entityManager.createQuery(Group.FIND_ALL.QUERY, Group.class)
                .getResultList();
    }

    public List<Option> findOptionByGroupId(Long groupId) {
        return entityManager.createQuery(Option.FIND_BY_GROUP_ID.QUERY, Option.class)
                .setParameter(Option.FIND_BY_GROUP_ID.PARAM_GROUP_ID, groupId)
                .getResultList();
    }

    public Group update(Group group) {
        return entityManager.merge(group);
    }
}
