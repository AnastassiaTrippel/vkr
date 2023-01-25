package com.example.muivvkr.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ResponseDto implements Serializable {
    private Long numberItem;
    private String title;
    private Long cost;
    private String urlPhoto;
}
