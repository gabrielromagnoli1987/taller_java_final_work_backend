package com.petclinic.petclinic.persistence.dao.impl;

import com.petclinic.petclinic.models.Role;
import com.petclinic.petclinic.persistence.dao.RoleDAO;
import com.petclinic.petclinic.persistence.utils.EntityManagerFactoryUtils;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Collection;
import java.util.List;

public class RoleDAOHibernateJPA extends GenericDAOHibernateJPA<Role> implements RoleDAO {

    public RoleDAOHibernateJPA() {
        super(Role.class);
    }

    @Override
    public Role findByName(String name) {
        EntityManager entityManager = EntityManagerFactoryUtils.getEntityManagerFactory().createEntityManager();
        Role role = null;
        try {
            role = (Role) entityManager.createQuery("SELECT role FROM Role role WHERE role.name = ?1")
                    .setParameter(1, name)
                    .getSingleResult();
        } catch (NoResultException e) {
            // using Optional, so no runtime exception needed here
        }
        entityManager.close();
        return role;
    }

    @Override
    public Collection<Role> findByUserId(Long userId) {
        EntityManager entityManager = EntityManagerFactoryUtils.getEntityManagerFactory().createEntityManager();

        List<Role> roles = (List<Role>) entityManager.createQuery("SELECT role FROM Role role LEFT JOIN role.users users WHERE users.id = ?1")
                .setParameter(1, userId)
                .getResultList();
        entityManager.close();
        return roles;
    }
}
