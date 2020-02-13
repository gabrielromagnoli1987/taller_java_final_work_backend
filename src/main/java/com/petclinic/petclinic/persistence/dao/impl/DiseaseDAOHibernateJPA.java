package com.petclinic.petclinic.persistence.dao.impl;

import com.petclinic.petclinic.models.Disease;
import com.petclinic.petclinic.persistence.dao.DiseaseDAO;

public class DiseaseDAOHibernateJPA extends GenericDAOHibernateJPA<Disease> implements DiseaseDAO {

    public DiseaseDAOHibernateJPA() {
        super(Disease.class);
    }
}
