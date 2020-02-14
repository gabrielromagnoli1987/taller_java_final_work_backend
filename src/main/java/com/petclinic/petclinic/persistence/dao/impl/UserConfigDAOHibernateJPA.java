package com.petclinic.petclinic.persistence.dao.impl;

import com.petclinic.petclinic.models.UserConfig;
import com.petclinic.petclinic.persistence.dao.UserConfigDAO;
import org.springframework.stereotype.Repository;

@Repository
public class UserConfigDAOHibernateJPA extends GenericDAOHibernateJPA<UserConfig> implements UserConfigDAO {

    public UserConfigDAOHibernateJPA() {
        super(UserConfig.class);
    }
}
