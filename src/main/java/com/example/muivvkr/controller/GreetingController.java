package com.example.muivvkr.controller;

import com.example.muivvkr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class GreetingController {

    private final UserService userService;

    @GetMapping("/")
    public String greeting(Model model) {
        if (userService.isAdmin()) {
            model.addAttribute("isAdmin",true);
        } else {
            model.addAttribute("isAdmin",false);
        }

        return "greeting";
    }
}