package com.example.muivvkr.entity;

import com.example.muivvkr.dto.ResponseDto;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
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

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

    public Long getNumberItem() {
        return numberItem;
    }

    public void setNumberItem(Long numberItem) {
        this.numberItem = numberItem;
    }
}