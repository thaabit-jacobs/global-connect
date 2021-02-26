package com.global.connect.model;

import com.global.connect.type.SubmissionType;
import lombok.Data;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@DiscriminatorValue("POST")
public class Post extends Submission{

    @Transient
    private SubmissionType submissionType = SubmissionType.POST;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public Post(){
        this.setLikes(0l);
        this.setDislikes(0l);
    }

    public Post(String content) {
        super(content);
        this.setLikes(0l);
        this.setDislikes(0l);
    }
}


