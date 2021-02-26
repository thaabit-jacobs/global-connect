package com.global.connect.repository;

import com.global.connect.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {


    Optional<Person> findByUserName(String username);
}
