package com.petclinic.petclinic.persistence.dao.impl;

import com.petclinic.petclinic.models.User;
import com.petclinic.petclinic.persistence.dao.UserDAO;
import com.petclinic.petclinic.persistence.utils.EntityManagerFactoryUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOHibernateJPA extends GenericDAOHibernateJPA<User> implements UserDAO {

    public UserDAOHibernateJPA() {
        super(User.class);
    }

//    @PersistenceContext
//    private EntityManager entityManager;

    @Override
    public Optional<User> findByEmail(String email) {
        EntityManager entityManager = EntityManagerFactoryUtils.getEntityManagerFactory().createEntityManager();
        //EntityManager entityManager = this.getEntityManager();
        User user = null;
        try {
            user = entityManager.createQuery("SELECT user FROM User user WHERE user.email = ?1", User.class)
                    .setParameter(1, email)
                    .getSingleResult();
        } catch (NoResultException e) {
            // using Optional, exception handled in the caller
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Page<User> findByIsVetEnabledIsNotNull(Pageable pageable) {
        EntityManager entityManager = EntityManagerFactoryUtils.getEntityManagerFactory().createEntityManager();
        //EntityManager entityManager = this.getEntityManager();
        List<User> users = entityManager.createQuery("SELECT user FROM User user WHERE user.isVetEnabled IS NOT NULL", User.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        entityManager.close();
        return new PageImpl<>(users, pageable, users.size());
    }

    @Override
    public Page<User> findByIsVetEnabledTrue(Pageable pageable) {
        EntityManager entityManager = EntityManagerFactoryUtils.getEntityManagerFactory().createEntityManager();
        //EntityManager entityManager = this.getEntityManager();
        List<User> users = entityManager.createQuery("SELECT user FROM User user WHERE user.isVetEnabled = TRUE", User.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        entityManager.close();
        return new PageImpl<>(users, pageable, users.size());
    }
}
