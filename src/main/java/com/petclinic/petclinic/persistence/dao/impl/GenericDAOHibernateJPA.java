package com.petclinic.petclinic.persistence.dao.impl;

import com.petclinic.petclinic.persistence.dao.GenericDAO;
import com.petclinic.petclinic.persistence.utils.EntityManagerFactoryUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public class GenericDAOHibernateJPA<T> implements GenericDAO<T> {

    protected Class<T> persistentClass;

    public GenericDAOHibernateJPA(Class<T> clazz) {
        persistentClass = clazz;
    }

    @Override
    public T create(T entity) {
        EntityManager entityManager = EntityManagerFactoryUtils.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(entity);
            entityTransaction.commit();
        }
        catch (RuntimeException e) {
            if (entityTransaction != null && entityTransaction.isActive()) entityTransaction.rollback();
            throw e;
        }
        finally {
            entityManager.close();
        }
        return entity;
    }

    @Override
    public Optional<T> retrieve(Serializable serializable) {
        EntityManager entityManager = EntityManagerFactoryUtils.getEntityManagerFactory().createEntityManager();
        T entity = entityManager.find(persistentClass, serializable);
        entityManager.close();
        return Optional.ofNullable(entity);
    }

    @Override
    public void delete(T entity) {
        EntityManager entityManager = EntityManagerFactoryUtils.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.remove(entity);
            entityTransaction.commit();
        }
        catch (RuntimeException e) {
            if (entityTransaction != null && entityTransaction.isActive()) entityTransaction.rollback();
            throw e;
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public T update(Serializable serializable, T entity) {
        EntityManager entityManager = EntityManagerFactoryUtils.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        T mergedEntity = entityManager.merge(entity);
        entityTransaction.commit();
        entityManager.close();
        return mergedEntity;
    }

    @Override
    public List<T> findAll() {
        EntityManager entityManager = EntityManagerFactoryUtils.getEntityManagerFactory().createEntityManager();
        List result = entityManager.createQuery("SELECT c FROM " + this.persistentClass.getSimpleName() + " c")
                .getResultList();
        entityManager.close();
        return result;
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        EntityManager entityManager = EntityManagerFactoryUtils.getEntityManagerFactory().createEntityManager();
        List result = entityManager.createQuery("SELECT c FROM " + this.persistentClass.getSimpleName() + " c")
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        entityManager.close();
        return new PageImpl<T>(result);
    }
}