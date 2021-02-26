package com.global.connect.controller;

import com.global.connect.model.Comment;
import com.global.connect.model.Person;
import com.global.connect.model.Post;
import com.global.connect.model.Submission;
import com.global.connect.repository.PersonRepository;
import com.global.connect.service.PersonService;
import com.global.connect.service.SubmissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@SessionAttributes("person")
public class HomeController {

    @Autowired
    private PersonService personService;

    @Autowired
    private SubmissionService submissionService;

    @GetMapping("/")
    public String showFeed(Model model, @ModelAttribute Person person){
        return listByPage(model, person, 1);
    }

    @GetMapping("/page/{pageNumber}")
    public String listByPage(Model model, @ModelAttribute Person person, @PathVariable("pageNumber") int currentPage){
        Page<Post> page = submissionService.findAllPostPage(currentPage);
        List<Post> postList = page.getContent();

        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("post", new Post());
        model.addAttribute("postList", postList);
        model.addAttribute("username", personService.getPersonName(person));

        return "homeView";
    }

    @PostMapping("/posts")
    public String addPost(Post post, @ModelAttribute Person person){
        person = personService.getPerson(person);

        person.getPosts().add(post);
        post.setPerson(person);

        personService.savePerson(person);

        return  "redirect:/";
    }


    @GetMapping("/posts/{postId}")
    public String showCommentsForPost(Model model, @PathVariable Long postId){
        List<Comment> commentList = submissionService.findAllCommentsByPostId(postId);

        Post post = (Post) submissionService.findById(postId);

        model.addAttribute("comment", new Comment());
        model.addAttribute("post", post);
        model.addAttribute("commentList", commentList);

        return "commentView";
    }

    @PostMapping("/comment/{postId}")
    public String addComment(Comment comment, @ModelAttribute Person person, @PathVariable Long postId){
        person = personService.findByUserName(person.getUserName()).get();

        Post post = (Post) submissionService.findById(postId);

        comment.setPerson(person);
        comment.setPost(post);

        submissionService.saveComment(comment);

        return  "redirect:/home/" + postId;
    }

    @GetMapping("/post/like/{postId}")
    public String addLikeToPost(@PathVariable("postId") Long postId, @ModelAttribute Person person){
        person = personService.findByUserName(person.getUserName()).get();

        Post post = (Post)submissionService.findById(postId);
        submissionService.incrementLikes(post);

        submissionService.savePost(post);

        return "redirect:/home";
        }


    @GetMapping("/post/dislike/{postId}")
    public String minusLikeFromPost(@PathVariable("postId") Long postId, @ModelAttribute Person person){
        person = personService.findByUserName(person.getUserName()).get();

        Post post = (Post) submissionService.findById(postId);
        submissionService.incrementdisLikes(post);

        submissionService.savePost(post);

        return "redirect:/home";
    }

    @GetMapping("/comment/like/{commentId}")
    public String addLikeToComment(@PathVariable("commentId") Long commentId, @ModelAttribute Person person){
        person = personService.findByUserName(person.getUserName()).get();

        Comment comment = (Comment) submissionService.findById(commentId);
        submissionService.incrementLikes(comment);

        submissionService.saveComment(comment);

        return "redirect:/home/" + comment.getPost().getId();
    }
}
