package com.global.connect.controller;

import com.global.connect.model.User;
import com.global.connect.repository.UserRepository;
import com.global.connect.type.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String showAdminView(Model model){
        List<Role> roleList = new ArrayList<>();

        List<User> userList = userRepository.findAll();

        for (Role role: Role.values()){
            roleList.add(role);
        }

        model.addAttribute("userList", userList);
        model.addAttribute("roleList", roleList);
        model.addAttribute("user", new User());

        return "adminView";
    }

    @PostMapping
    public String addUser(User user){


        user.setRole(Role.ADMIN);

        userRepository.save(user);

        return "redirect:/admin";
    }
}
