package com.petclinic.petclinic.persistence.dao;

import com.petclinic.petclinic.models.Role;

import java.util.Collection;

public interface RoleDAO extends GenericDAO<Role> {

    Role findByName(String name);

    Collection<Role> findByUserId(Long userId);

}
