package com.petclinic.petclinic.persistence.dao.impl;

import com.petclinic.petclinic.models.Privilege;
import com.petclinic.petclinic.persistence.dao.PrivilegeDAO;
import com.petclinic.petclinic.persistence.utils.EntityManagerFactoryUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class PrivilegeDAOHibernateJPA extends GenericDAOHibernateJPA<Privilege> implements PrivilegeDAO {

    public PrivilegeDAOHibernateJPA() {
        super(Privilege.class);
    }

//    @PersistenceContext
//    private EntityManager entityManager;

    @Override
    public Privilege findByName(String name) {
        EntityManager entityManager = EntityManagerFactoryUtils.getEntityManagerFactory().createEntityManager();
        //EntityManager entityManager = this.getEntityManager();
        Privilege privilege = null;
        try {
            privilege = entityManager.createQuery("SELECT privilege FROM Privilege privilege WHERE privilege.name = ?1", Privilege.class)
                    .setParameter(1, name)
                    .getSingleResult();
        } catch (NoResultException e) {

        } finally {
            entityManager.close();
        }
        return privilege;
    }
}
