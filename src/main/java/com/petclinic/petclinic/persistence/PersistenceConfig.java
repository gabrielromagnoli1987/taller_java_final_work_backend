//package com.petclinic.petclinic.persistence;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//public class PersistenceConfig {
//
//    private static final String MODELS_PACKAGE = "com.petclinic.petclinic.models";
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//        emf.setDataSource(dataSource());
//        emf.setPackagesToScan(MODELS_PACKAGE);
//        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
//        emf.setJpaVendorAdapter(jpaVendorAdapter);
//        emf.setJpaProperties(additionalProperties());
//        return emf;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//        driverManagerDataSource.setUsername("root");
//        driverManagerDataSource.setPassword("password");
//        driverManagerDataSource.setUrl("jdbc:mysql://pet_clinic_db:3306/petclinic");
//        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        return driverManagerDataSource;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
//        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
//        jpaTransactionManager.setEntityManagerFactory(emf);
//        return jpaTransactionManager;
//    }
//
//    private Properties additionalProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.hbm2ddl.auto", "update");
//        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL55Dialect");
//        properties.setProperty("hibernate.default_schema", "petclinic");
//        return properties;
//    }
//}
