package com.petclinic.petclinic.persistence.utils;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Component
public class EntityManagerFactoryUtils {

    private static EntityManagerFactory entityManagerFactory;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        EntityManagerFactoryUtils.entityManagerFactory = entityManagerFactory;
    }

    // private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");

    public static EntityManagerFactory getEntityManagerFactory () {
        return entityManagerFactory;
    }

}
