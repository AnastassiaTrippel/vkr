package com.example.muivvkr.controller;

import com.example.muivvkr.entity.ItemEntity;
import com.example.muivvkr.entity.OrderEntity;
import com.example.muivvkr.entity.OrganizationEntity;
import com.example.muivvkr.entity.UserEntity;
import com.example.muivvkr.service.ItemService;
import com.example.muivvkr.service.OrganizationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
public class ItemController {

    private final ItemService itemService;
    private final List<ItemEntity> items = new ArrayList<>();

    @GetMapping
    public String getItems(Model model) throws JsonProcessingException {
        items.clear();
        items.addAll(itemService.getItems());
        model.addAttribute("items", items);
        return "items";
    }

    @PostMapping
    public String checkout(@RequestParam int[] checkboxes, @RequestParam String[] quantity, Model model) {
        itemService.checkout(checkboxes, quantity, items);
        model.addAttribute("items", items);

        return "items";
    }
}