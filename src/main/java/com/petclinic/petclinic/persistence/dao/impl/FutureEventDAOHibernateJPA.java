package com.petclinic.petclinic.persistence.dao.impl;

import com.petclinic.petclinic.models.FutureEvent;
import com.petclinic.petclinic.persistence.dao.FutureEventDAO;
import org.springframework.stereotype.Repository;

@Repository
public class FutureEventDAOHibernateJPA extends GenericDAOHibernateJPA<FutureEvent> implements FutureEventDAO {

    public FutureEventDAOHibernateJPA() {
        super(FutureEvent.class);
    }
}
