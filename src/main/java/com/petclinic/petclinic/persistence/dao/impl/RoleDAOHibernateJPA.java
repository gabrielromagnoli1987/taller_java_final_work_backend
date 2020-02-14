package com.petclinic.petclinic.persistence.dao.impl;

import com.petclinic.petclinic.models.Role;
import com.petclinic.petclinic.persistence.dao.RoleDAO;
import com.petclinic.petclinic.persistence.utils.EntityManagerFactoryUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;

@Repository
public class RoleDAOHibernateJPA extends GenericDAOHibernateJPA<Role> implements RoleDAO {

    public RoleDAOHibernateJPA() {
        super(Role.class);
    }

//    @PersistenceContext
//    private EntityManager entityManager;

    @Override
    public Role findByName(String name) {
        EntityManager entityManager = EntityManagerFactoryUtils.getEntityManagerFactory().createEntityManager();
        //EntityManager entityManager = this.getEntityManager();
        Role role = null;
        try {
            role = entityManager.createQuery("SELECT role FROM Role role WHERE role.name = ?1", Role.class)
                    .setParameter(1, name)
                    .getSingleResult();
        } catch (NoResultException e) {

        } finally {
            entityManager.close();
        }
        return role;
    }

    @Override
    public Collection<Role> findByUserId(Long userId) {
        EntityManager entityManager = EntityManagerFactoryUtils.getEntityManagerFactory().createEntityManager();
        //EntityManager entityManager = this.getEntityManager();
        List<Role> roles = entityManager.createQuery("SELECT role FROM Role role LEFT JOIN role.users users WHERE users.id = ?1", Role.class)
                .setParameter(1, userId)
                .getResultList();
        entityManager.close();
        return roles;
    }
}
