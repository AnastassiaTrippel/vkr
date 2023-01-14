package com.example.muivvkr.service;

import com.example.muivvkr.entity.ItemEntity;
import com.example.muivvkr.entity.OrderEntity;
import com.example.muivvkr.entity.OrganizationEntity;
import com.example.muivvkr.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;

    public void add(List<ItemEntity> items) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCost(items.stream().map(itemEntity -> itemEntity.getCost() * itemEntity.getQuantity()).mapToLong(Long::intValue).sum());
        orderEntity.setUsername(userService.getCurrentUserName());
        orderEntity.setItems(items);

        orderRepository.save(orderEntity);
    }

    public List<OrderEntity> getOrders() {
        List<OrderEntity> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders;
    }
}