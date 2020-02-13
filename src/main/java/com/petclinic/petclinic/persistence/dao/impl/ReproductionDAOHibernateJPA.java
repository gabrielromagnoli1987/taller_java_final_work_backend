package com.petclinic.petclinic.persistence.dao.impl;

import com.petclinic.petclinic.models.Reproduction;
import com.petclinic.petclinic.persistence.dao.ReproductionDAO;

public class ReproductionDAOHibernateJPA extends GenericDAOHibernateJPA<Reproduction> implements ReproductionDAO {

    public ReproductionDAOHibernateJPA() {
        super(Reproduction.class);
    }
}
