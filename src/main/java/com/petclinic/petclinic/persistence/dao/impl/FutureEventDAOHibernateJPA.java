package com.petclinic.petclinic.persistence.dao.impl;

import com.petclinic.petclinic.models.FutureEvent;
import com.petclinic.petclinic.persistence.dao.FutureEventDAO;

public class FutureEventDAOHibernateJPA extends GenericDAOHibernateJPA<FutureEvent> implements FutureEventDAO {

    public FutureEventDAOHibernateJPA() {
        super(FutureEvent.class);
    }
}
