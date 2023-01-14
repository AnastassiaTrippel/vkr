package com.example.muivvkr.dto;

import java.io.Serializable;

public class ResponseDto implements Serializable {
    private Long numberItem;
    private String title;
    private Long cost;
    private String urlPhoto;

    public Long getNumberItem() {
        return numberItem;
    }

    public void setNumberItem(Long numberItem) {
        this.numberItem = numberItem;
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
}
