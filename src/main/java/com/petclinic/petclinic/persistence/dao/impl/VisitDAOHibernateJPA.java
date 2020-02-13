package com.petclinic.petclinic.persistence.dao.impl;

import com.petclinic.petclinic.models.Visit;
import com.petclinic.petclinic.persistence.dao.VisitDAO;

public class VisitDAOHibernateJPA extends GenericDAOHibernateJPA<Visit> implements VisitDAO {

    public VisitDAOHibernateJPA() {
        super(Visit.class);
    }
}
