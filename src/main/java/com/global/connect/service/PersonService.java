package com.global.connect.service;

import com.global.connect.exception.PersonNotFoundException;
import com.global.connect.model.Person;
import com.global.connect.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person savePerson(Person person){
      return  personRepository.save(person);
    }

    public Optional<Person> findByUserName(String username) {
        if (doesPersonExist(username)){
            return personRepository.findByUserName(username);
        }

      return Optional.empty();
    }

    public boolean doesPersonExist(String username){
        return personRepository.findByUserName(username).isPresent();
    }

    public String getPersonName(Person person){
        return this.findByUserName(person.getUserName()).get().getUserName();
    }

    public Person getPerson(Person person){
        return this.findByUserName(person.getUserName()).get();
    }

}
