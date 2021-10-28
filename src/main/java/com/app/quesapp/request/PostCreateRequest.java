package com.app.quesapp.request;

import lombok.Data;

@Data
public class PostCreateRequest {
    private Long id;
    private  String title;
    private String text;
    private Long userId;
}
