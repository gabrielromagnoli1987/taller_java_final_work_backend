package com.petclinic.petclinic.persistence.dao;

import com.petclinic.petclinic.models.Privilege;

public interface PrivilegeDAO extends GenericDAO<Privilege> {

    Privilege findByName(String name);

}
