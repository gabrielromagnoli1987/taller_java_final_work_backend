package com.petclinic.petclinic.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserConfig {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "userConfig")
    private User user;

    private Boolean showLastName;

    private Boolean showEmail;

    private Boolean showPhone;

    public UserConfig() {

    }

    public UserConfig(Boolean showLastName, Boolean showEmail, Boolean showPhone) {
        this.showLastName = showLastName;
        this.showEmail = showEmail;
        this.showPhone = showPhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getShowLastName() {
        return showLastName;
    }

    public void setShowLastName(Boolean showLastName) {
        this.showLastName = showLastName;
    }

    public Boolean getShowEmail() {
        return showEmail;
    }

    public void setShowEmail(Boolean showEmail) {
        this.showEmail = showEmail;
    }

    public Boolean getShowPhone() {
        return showPhone;
    }

    public void setShowPhone(Boolean showPhone) {
        this.showPhone = showPhone;
    }
}
