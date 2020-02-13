package com.petclinic.petclinic.persistence.dao.impl;

import com.petclinic.petclinic.models.Dewormed;
import com.petclinic.petclinic.persistence.dao.DewormingDAO;

public class DewormingDAOHibernateJPA extends GenericDAOHibernateJPA<Dewormed> implements DewormingDAO {

    public DewormingDAOHibernateJPA() {
        super(Dewormed.class);
    }

}
