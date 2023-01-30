package com.example.muivvkr.controller;

import com.example.muivvkr.entity.ItemEntity;
import com.example.muivvkr.entity.OrderEntity;
import com.example.muivvkr.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @PostMapping
    public String getOrderHistory(@RequestParam Integer id, Model model) {
        List<ItemEntity> items = orderService.getHistory(id);
        model.addAttribute("items", items);
        model.addAttribute("id", id);

        return "history-order";
    }
}