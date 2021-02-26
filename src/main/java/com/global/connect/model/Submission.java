package com.global.connect.model;

import com.global.connect.type.Role;
import com.global.connect.type.SubmissionType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "submissions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "submission_type",
discriminatorType = DiscriminatorType.STRING)
@Data
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "content")
    private String content;

    @Column(name = "likes")
    private Long likes;

    @Column(name = "dislikes")
    private Long dislikes;

    @Column(name = "created_at")
    private Date createdAt;

    public Submission() {
    }

    public Submission(String content) {
        this.content = content;
        this.likes = 0l;
        this.dislikes = 0l;
    }

    @PrePersist
    void createdAt(){
        this.createdAt = new Date();
    }
}
