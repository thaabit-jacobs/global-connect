package com.global.connect.model;

import com.global.connect.type.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@DiscriminatorValue("USER")
public class User extends Person{

    public User() {
    }

    public User(String firstName, String lastName, String email, String userName, Date dateOfBirth) {
        super(firstName, lastName, email, userName, dateOfBirth);
    }
}
