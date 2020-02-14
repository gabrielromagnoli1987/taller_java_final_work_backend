package com.petclinic.petclinic.persistence.dao.impl;

import com.petclinic.petclinic.models.Pet;
import com.petclinic.petclinic.persistence.dao.PetDAO;
import org.springframework.stereotype.Repository;

@Repository
public class PetDAOHibernateJPA extends GenericDAOHibernateJPA<Pet> implements PetDAO {

    public PetDAOHibernateJPA() {
        super(Pet.class);
    }
}
