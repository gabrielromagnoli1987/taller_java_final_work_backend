package com.petclinic.petclinic.persistence.dao.impl;

import com.petclinic.petclinic.models.User;
import com.petclinic.petclinic.persistence.dao.UserDAO;
import com.petclinic.petclinic.persistence.utils.EntityManagerFactoryUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class UserDAOHibernateJPA extends GenericDAOHibernateJPA<User> implements UserDAO {

    public UserDAOHibernateJPA() {
        super(User.class);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        EntityManager entityManager = EntityManagerFactoryUtils.getEntityManagerFactory().createEntityManager();
        User user = null;
        try {
            user = (User) entityManager.createQuery("SELECT user FROM User user WHERE user.email = ?1")
                    .setParameter(1, email)
                    .getSingleResult();
        } catch (NoResultException e) {
            // using Optional, so no runtime exception needed here
        }
        entityManager.close();
        return Optional.ofNullable(user);
    }

    @Override
    public Page<User> findByIsVetEnabledIsNotNull(Pageable pageable) {
        EntityManager entityManager = EntityManagerFactoryUtils.getEntityManagerFactory().createEntityManager();
        List<User> users = entityManager.createQuery("SELECT user FROM User user WHERE user.isVetEnabled IS NOT NULL", User.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        entityManager.close();
        return new PageImpl<>(users);
    }

    @Override
    public Page<User> findByIsVetEnabledTrue(Pageable pageable) {
        EntityManager entityManager = EntityManagerFactoryUtils.getEntityManagerFactory().createEntityManager();
        List<User> users = entityManager.createQuery("SELECT user FROM User user WHERE user.isVetEnabled = TRUE", User.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        entityManager.close();
        return new PageImpl<>(users);
    }
}
