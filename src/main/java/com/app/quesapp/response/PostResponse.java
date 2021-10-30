package com.app.quesapp.response;

import com.app.quesapp.model.Post;
import lombok.Data;

@Data
public class PostResponse {

    private Long id;
    private  Long userId;
    private String userName;
    private String text;
    private String title;



    public PostResponse(Post post){
        this.id = post.getId();
        this.userId = post.getUser().getId();
        this.userName = post.getUser().getUserName();
        this.text = post.getText();
        this.title = post.getTitle();
    }

}
