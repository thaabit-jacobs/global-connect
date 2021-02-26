package com.global.connect;

import com.global.connect.model.*;
import com.global.connect.repository.PersonRepository;
import com.global.connect.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class GlobalConnectApplication implements CommandLineRunner {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	SubmissionRepository submissionRepository;


	public static void main(String[] args) {
		SpringApplication.run(GlobalConnectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//String firstName, String lastName, String email, String userName, Date dateOfBirth
		Post post = new Post("THIS IS POST ONE");
		Post post1 = new Post("THIS IS POST TWO");
		Post post2 = new Post("THIS IS POST THREE");

		Comment comment = new Comment("THIS IS COMMENT ONE");
		Comment comment1 = new Comment("THIS IS COMMENT TWO");
		Comment comment2 = new Comment("THIS IS COMMENT THREE");


		Admin admin = new Admin("Thaabit", "Jacobs", "jacobs@gmail", "tapes", new Date());

		post.setPerson(admin);
		post1.setPerson(admin);
		post2.setPerson(admin);

		comment.setPerson(admin);
		comment1.setPerson(admin);
		comment2.setPerson(admin);

		comment.setPost(post);
		comment1.setPost(post1);
		comment2.setPost(post2);

		post.getComments().add(comment);
		post1.getComments().add(comment1);
		post2.getComments().add(comment2);

		admin.getPosts().add(post1);
		admin.getPosts().add(post);
		admin.getPosts().add(post2);

		personRepository.save(admin);
	}
}
