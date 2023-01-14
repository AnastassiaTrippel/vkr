package com.example.muivvkr.controller;

import com.example.muivvkr.entity.OrderEntity;
import com.example.muivvkr.entity.OrganizationEntity;
import com.example.muivvkr.service.OrderService;
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

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public String getOrder(Model model) {
        List<OrderEntity> orders = orderService.getOrders();
        model.addAttribute("orders", orders);
        return "orders";
    }
}