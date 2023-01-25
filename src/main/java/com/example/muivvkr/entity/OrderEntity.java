package com.example.muivvkr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<ItemEntity> items;
    private Long cost;
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity userEntity;
}