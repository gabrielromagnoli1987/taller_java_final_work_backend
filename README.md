### UNLP Java Workshop - Pet Clinic - Backend

**Requirements for building and running the project:** 

1. Install [docker-compose](https://docs.docker.com/compose/install/)
2. `git clone https://github.com/gabrielromagnoli1987/taller_java_final_work_backend.git` 
3. `cd taller_java_final_work_backend`
4. `mvn install` To build the project.
5. `docker-compose up` This will download a openjdk:11 image copy the application.jar to that image, a mysql server image, 
a nginx server image and will run the 3 containers connected on the same network.

This site was built using [spring-boot 2.1.8](https://start.spring.io/)

**Architecture**

![Alt text](Petclinic_architecture.png?raw=true "Architecture")

**Docs reference:**

- [UNLP](https://catedras.info.unlp.edu.ar/course/view.php?id=931)
- [Java Brains](https://www.youtube.com/user/koushks)
- https://www.baeldung.com/role-and-privilege-for-spring-security-registration 
- https://www.baeldung.com/spring-security-expressions
- https://www.baeldung.com/spring-security-method-security
- https://www.baeldung.com/java-bean-validation-not-null-empty-blank
- https://www.baeldung.com/exception-handling-for-rest-with-spring
- https://www.baeldung.com/hibernate-save-persist-update-merge-saveorupdate
- https://www.javainuse.com/spring/boot-jwt
- https://www.toptal.com/java/spring-boot-rest-api-error-handling
- [OneToMany jpa and hibernate](https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/)
- [ManyToMany jpa and hibernate](https://www.javaworld.com/article/3387643/java-persistence-with-jpa-and-hibernate-part-2-many-to-many-relationships.html)