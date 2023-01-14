package com.example.muivvkr.service;

import com.example.muivvkr.dto.ResponseDto;
import com.example.muivvkr.entity.ItemEntity;
import com.example.muivvkr.entity.OrganizationEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final OrderService orderService;
    private final OrganizationService organizationService;

    public List<ItemEntity> getItems() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        List<OrganizationEntity> organizations = organizationService.getOrganizations();
        ObjectMapper mapper = new ObjectMapper();
        List<ItemEntity> itemEntities = new ArrayList<>();

        for (OrganizationEntity org: organizations) {
            String ResourceUrl
                    = org.getUrl();
            ResponseEntity<String> response
                    = restTemplate.getForEntity(ResourceUrl + "/1", String.class);
            String body =response.getBody();

            List<ItemEntity> items = Arrays.stream(mapper.readValue(body, ResponseDto[].class))
                    .map(ItemEntity::new)
                    .collect(Collectors.toList());
            items.forEach(itemEntity -> itemEntity.setOrganization(org));
            itemEntities.addAll(items);
        }

        return itemEntities;
    }

    public void checkout(int[] checkboxes, String[] quantity, List<ItemEntity> items) {
        List<ItemEntity> checkedItems = new ArrayList<>();
        List<Integer> quan = Arrays.stream(quantity).filter(number -> !Objects.equals(number, ""))
                .mapToInt(Integer::valueOf).boxed()
                .collect(Collectors.toList());

        for (int i = 0; i < checkboxes.length; i++) {
            int itemNumber = checkboxes[i];
            ItemEntity tmp = items.stream().filter(itemEntity -> itemEntity.getNumberItem() == itemNumber).findFirst().get();
            ItemEntity newEntity = new ItemEntity();
            newEntity.setCost(tmp.getCost());
            newEntity.setTitle(tmp.getTitle());
            newEntity.setUrlPhoto(tmp.getUrlPhoto());
            newEntity.setQuantity(quan.get(i));
            newEntity.setNumberItem(tmp.getNumberItem());
            newEntity.setOrganization(tmp.getOrganization());

            checkedItems.add(newEntity);
        }

        orderService.add(checkedItems);
    }
}
