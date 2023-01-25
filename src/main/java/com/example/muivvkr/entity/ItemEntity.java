package com.example.muivvkr.entity;

import com.example.muivvkr.dto.ResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long numberItem;
    private String title;
    private Long cost;
    private String urlPhoto;
    private Integer quantity;
    @ManyToOne(fetch = FetchType.EAGER)
    private OrderEntity order;
    @ManyToOne(fetch = FetchType.EAGER)
    private OrganizationEntity organization;

    public ItemEntity(ResponseDto responseDto) {
        this.numberItem = responseDto.getNumberItem();
        this.title = responseDto.getTitle();
        this.cost = responseDto.getCost();
        this.urlPhoto = responseDto.getUrlPhoto();
    }
}