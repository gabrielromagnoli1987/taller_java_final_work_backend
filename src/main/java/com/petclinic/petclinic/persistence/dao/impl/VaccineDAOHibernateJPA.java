package com.petclinic.petclinic.persistence.dao.impl;

import com.petclinic.petclinic.models.Vaccine;
import com.petclinic.petclinic.persistence.dao.VaccineDAO;
import org.springframework.stereotype.Repository;

@Repository
public class VaccineDAOHibernateJPA extends GenericDAOHibernateJPA<Vaccine> implements VaccineDAO {

    public VaccineDAOHibernateJPA() {
        super(Vaccine.class);
    }
}
