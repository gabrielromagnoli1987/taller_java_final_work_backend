package com.petclinic.petclinic.persistence.utils;

import com.petclinic.petclinic.persistence.dao.*;
import com.petclinic.petclinic.persistence.dao.impl.*;

public class DAOFactory {

    public static UserDAO getUserDAO() {
        return new UserDAOHibernateJPA();
    }

    public static PetDAO getPetDAO() {
        return new PetDAOHibernateJPA();
    }

    public static DewormingDAO getDewormingDAO() {
        return new DewormingDAOHibernateJPA();
    }

    public static DiseaseDAO getDiseaseDAO() {
        return new DiseaseDAOHibernateJPA();
    }

    public static FutureEventDAO getFutureEventDAO() {
        return new FutureEventDAOHibernateJPA();
    }

    public static PrivilegeDAO getPrivilegeDAO() {
        return new PrivilegeDAOHibernateJPA();
    }

    public static ReproductionDAO getReproductionDAO() {
        return new ReproductionDAOHibernateJPA();
    }

    public static RoleDAO getRoleDAO() {
        return new RoleDAOHibernateJPA();
    }

    public static SurgerieDAO getSurgerieDAO() {
        return new SurgerieDAOHibernateJPA();
    }

    public static UserConfigDAO getUserConfigDAO() {
        return new UserConfigDAOHibernateJPA();
    }

    public static VaccineDAO getVaccineDAO() {
        return new VaccineDAOHibernateJPA();
    }

    public static VisitDAO getVisitDAO() {
        return new VisitDAOHibernateJPA();
    }
}
