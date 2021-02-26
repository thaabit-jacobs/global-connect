package com.global.connect.model;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Data
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Person{

    public Admin() {
    }

    public Admin(String firstName, String lastName, String email, String userName, Date dateOfBirth) {
        super(firstName, lastName, email, userName, dateOfBirth);
    }
}
