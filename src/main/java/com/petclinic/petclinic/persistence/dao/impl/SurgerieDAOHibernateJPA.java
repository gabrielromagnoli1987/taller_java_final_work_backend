package com.petclinic.petclinic.persistence.dao.impl;

import com.petclinic.petclinic.models.Surgery;
import com.petclinic.petclinic.persistence.dao.SurgerieDAO;
import org.springframework.stereotype.Repository;

@Repository
public class SurgerieDAOHibernateJPA extends GenericDAOHibernateJPA<Surgery> implements SurgerieDAO {

    public SurgerieDAOHibernateJPA() {
        super(Surgery.class);
    }
}
