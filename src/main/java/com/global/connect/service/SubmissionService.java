package com.global.connect.service;


import com.global.connect.model.Comment;
import com.global.connect.model.Post;
import com.global.connect.model.Submission;
import com.global.connect.repository.SubmissionRepository;
import com.global.connect.type.Role;
import com.global.connect.type.SubmissionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

@Slf4j
@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;


    public Page<Post> findAllPostPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return extractPage(pageable, this.findAllPost());
    }

    public static Page<Post> extractPage(Pageable page, List<Post> contents) {
        int startIndex = page.getPageNumber() == 0 ? page.getPageNumber() : page.getPageNumber() + page.getPageSize();
        int toIndex = startIndex + page.getPageSize();
        toIndex = toIndex > contents.size() ? contents.size() : toIndex;

        final int total = contents.size();

        List<Post> filteredContents;

        if (startIndex < contents.size()) {
            filteredContents = contents.subList(startIndex, toIndex);
        } else {
            filteredContents = new ArrayList<>();
        }

        LongSupplier totalSupplier = () -> {
            return total;
        };

        return PageableExecutionUtils.getPage(filteredContents, gotoPage(page.getPageNumber(), page.getPageSize()),
                totalSupplier);
    }

    private static PageRequest gotoPage(int number, int size) {
        return PageRequest.of(number, size);
    }

    public List<Post> findAllPost(){
        List<Post> postList  = submissionRepository.findAllPost();

        return postList;
    }

    public List<Comment> findAllCommentsByPostId(Long postId){
        return submissionRepository.findAllCommentsByPostId(postId);
    }

    public Submission findById(Long submssionId){
        return submissionRepository.findById(submssionId).get();
    }


    public Post savePost(Post post){
        return submissionRepository.save(post);
    }

    public Comment saveComment(Comment comment){
        return submissionRepository.save(comment);
    }

    public Long incrementLikes(Submission submission){
        long currentLikes = submission.getLikes();
         submission.setLikes(++currentLikes);

        return submission.getLikes();
    }

    public Long incrementdisLikes(Submission submission){
        long currentDisLikes = submission.getDislikes();

        submission.setDislikes(++currentDisLikes);

        return submission.getDislikes();
    }


}
