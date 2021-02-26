package com.global.connect.controller;

import com.global.connect.model.Person;
import com.global.connect.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/login")
@SessionAttributes("person")
public class LoginController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public String showLoginView(Model model){
        model.addAttribute("person", new Person());

        return "login";
    }

    @PostMapping
    public String checkPersonCredentials(Person person){
        if (!personService.doesPersonExist(person.getUserName())){
            return "redirect:/login";
        }

        return "redirect:/";
    }
}
