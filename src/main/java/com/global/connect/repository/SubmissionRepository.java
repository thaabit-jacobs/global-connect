package com.global.connect.repository;

import com.global.connect.model.Comment;
import com.global.connect.model.Post;
import com.global.connect.model.Submission;
import com.global.connect.type.Role;
import com.global.connect.type.SubmissionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    @Query(value = "select * from submissions where submission_type='POST' order by created_at desc",
            nativeQuery = true)
    List<Post> findAllPost();

    @Query(value = "select * from submissions where submission_type='COMMENT' and post_id=:postId order by created_at desc",
    nativeQuery = true)
    List<Comment> findAllCommentsByPostId(@Param("postId") Long postId);
}
