package com.petclinic.petclinic.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class UserConfig {

    @OneToOne(mappedBy = "userConfig")
    private User user;

    private Boolean showLastname;

    private Boolean showEmail;

    private Boolean showPhone;

    public UserConfig() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getShowLastname() {
        return showLastname;
    }

    public void setShowLastname(Boolean showLastname) {
        this.showLastname = showLastname;
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
