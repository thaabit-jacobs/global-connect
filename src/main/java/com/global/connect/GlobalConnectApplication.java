package com.global.connect;

import com.global.connect.model.Comment;
import com.global.connect.model.Post;
import com.global.connect.model.User;
import com.global.connect.repository.CommentRepository;
import com.global.connect.repository.PostRepository;
import com.global.connect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class GlobalConnectApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PostRepository postRepository;

	@Autowired
	CommentRepository commentRepository;

	public static void main(String[] args) {
		SpringApplication.run(GlobalConnectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//String firstName, String lastName, String email, String userName, Date dateOfBirth
		Post post = new Post("this is a text post");
		Post post1 = new Post("this is a second post");

		Comment comment = new Comment("this a comment 0");
		Comment comment1 = new Comment("this a comment 1");
		Comment comment2 = new Comment("this a comment 2");


		User user = new User("Thaabit", "Jacobs", "jacobs@gmail", "tapes", new Date());

		post.setUser(user);
		post1.setUser(user);

		userRepository.save(user);

		postRepository.save(post);
		postRepository.save(post1);

		comment.setPost(post);
		comment.setUser(user);

		comment1.setPost(post);
		comment1.setUser(user);

		comment2.setPost(post1);
		comment2.setUser(user);

		commentRepository.save(comment);
		commentRepository.save(comment1);
		commentRepository.save(comment2);
	}
}
