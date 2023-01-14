package com.example.muivvkr.controller;

import com.example.muivvkr.entity.UserEntity;
import com.example.muivvkr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String greeting(Model model) {
        List<UserEntity> users = userService.getUsers();
        model.addAttribute("users", users);
        return "user-list";
    }

    @PostMapping
    public String greeting(@RequestParam String username, @RequestParam String password, Map<String, Object> model) {
        userService.add(username, password);
        List<UserEntity> users = userService.getUsers();
        model.put("users", users);
        return "user-list";
    }
}