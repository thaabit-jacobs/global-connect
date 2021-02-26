package com.global.connect.controller;

import com.global.connect.model.Admin;
import com.global.connect.model.Person;
import com.global.connect.model.User;
import com.global.connect.repository.PersonRepository;
import com.global.connect.type.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public String showAdminView(Model model){
        List<Role> roleList = new ArrayList<>();

        List<Person> personList = personRepository.findAll();

        for (Role role: Role.values()){
            roleList.add(role);
        }

        model.addAttribute("personList", personList);
        model.addAttribute("roleList", roleList);
        model.addAttribute("user", new Person());

        return "adminView";
    }

    @PostMapping
    public String addPerson(Admin admin){

        personRepository.save(admin);

        return "redirect:/admin";
    }
}
