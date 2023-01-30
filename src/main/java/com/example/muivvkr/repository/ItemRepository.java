package com.example.muivvkr.repository;


import com.example.muivvkr.entity.ItemEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<ItemEntity, Integer> {

    List<ItemEntity> findAllByOrderId(Long order_id);
}
