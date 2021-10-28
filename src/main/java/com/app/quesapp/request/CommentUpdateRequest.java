package com.app.quesapp.request;

import lombok.Data;
import org.springframework.context.annotation.Primary;

@Data
public class CommentUpdateRequest {
    private String text;

}
