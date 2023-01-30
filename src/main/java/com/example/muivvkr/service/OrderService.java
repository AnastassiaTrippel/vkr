package com.example.muivvkr.service;

import com.example.muivvkr.entity.ItemEntity;
import com.example.muivvkr.entity.OrderEntity;
import com.example.muivvkr.entity.UserEntity;
import com.example.muivvkr.repository.ItemRepository;
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
    private final ItemRepository itemRepository;

    public void add(List<ItemEntity> items) {
        UserEntity currentUser = userService.findByUsername(userService.getCurrentUserName());

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCost(items.stream().map(itemEntity -> itemEntity.getCost() * itemEntity.getQuantity()).mapToLong(Long::intValue).sum());
        orderEntity.setUserEntity(currentUser);
        OrderEntity order = orderRepository.save(orderEntity);

        items.forEach(ord -> ord.setOrder(order));
        order.setItems(items);

        orderRepository.save(order);
    }

    public List<OrderEntity> getOrders() {
        List<OrderEntity> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders;
    }

    public List<ItemEntity> getHistory(Integer id) {
        return itemRepository.findAllByOrderId(Long.valueOf(id));
    }
}
