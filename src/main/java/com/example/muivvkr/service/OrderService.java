package com.example.muivvkr.service;

import com.example.muivvkr.entity.ItemEntity;
import com.example.muivvkr.entity.OrderEntity;
import com.example.muivvkr.entity.OrganizationEntity;
import com.example.muivvkr.entity.UserEntity;
import com.example.muivvkr.repository.ItemRepository;
import com.example.muivvkr.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

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

        sendOrder(items);

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

    private void sendOrder(List<ItemEntity> items) {
        RestTemplate restTemplate = new RestTemplate();
        Set<OrganizationEntity> organizations = items.stream().map(ItemEntity::getOrganization).collect(Collectors.toSet());


        for(OrganizationEntity org: organizations) {
            String url = org.getDispatch();
            List<ItemEntity> filteredItems = items.stream()
                    .filter(item -> Objects.equals(item.getOrganization().getId(), org.getId()))
                    .collect(Collectors.toList());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<>(filteredItems, headers);
            restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<>() {});

        }
    }
}
