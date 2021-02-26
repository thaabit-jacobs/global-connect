package com.global.connect.model;

import com.global.connect.type.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role",
        discriminatorType = DiscriminatorType.STRING)
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "user_name")
    private String userName;


    @Column(name = "created_At")
    private Date createdAt;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public Person() {
    }

    public Person(String firstName, String lastName, String email, String userName, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
    }

    @PrePersist
    void createdAt(){
        this.createdAt = new Date();
    }
}
