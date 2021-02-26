package com.global.connect.model;

import com.global.connect.type.SubmissionType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data
@DiscriminatorValue("COMMENT")
@Entity
public class Comment extends Submission{

    @Transient
    private SubmissionType submissionType = SubmissionType.COMMENT;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(){
        this.setLikes(0l);
        this.setDislikes(0l);
    }

    public Comment(String content) {
        super(content);
        this.setLikes(0l);
        this.setDislikes(0l);
    }

}
