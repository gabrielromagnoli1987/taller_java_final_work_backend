package com.petclinic.petclinic.persistence.dao.impl;

import com.petclinic.petclinic.models.Privilege;
import com.petclinic.petclinic.persistence.dao.PrivilegeDAO;
import com.petclinic.petclinic.persistence.utils.EntityManagerFactoryUtils;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class PrivilegeDAOHibernateJPA extends GenericDAOHibernateJPA<Privilege> implements PrivilegeDAO {

    public PrivilegeDAOHibernateJPA() {
        super(Privilege.class);
    }

    @Override
    public Privilege findByName(String name) {
        EntityManager entityManager = EntityManagerFactoryUtils.getEntityManagerFactory().createEntityManager();
        Privilege privilege = null;
        try {
            privilege = (Privilege) entityManager.createQuery("SELECT privilege FROM Privilege privilege WHERE privilege.name = ?1")
                    .setParameter(1, name)
                    .getSingleResult();
        } catch (NoResultException e) {
            // using Optional, so no runtime exception needed here
        }
        entityManager.close();
        return privilege;
    }
}
