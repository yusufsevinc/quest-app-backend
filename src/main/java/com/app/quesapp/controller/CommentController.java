package com.app.quesapp.controller;

import com.app.quesapp.model.Comment;
import com.app.quesapp.model.Post;
import com.app.quesapp.request.CommentCreateRequest;
import com.app.quesapp.request.CommentUpdateRequest;
import com.app.quesapp.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping
    public List<Comment> getAllComment(@RequestParam Optional<Long> userId , @RequestParam
                                       Optional<Long> postId){
        return commentService.getAllCommentWithParam(userId,postId);
    }

    @GetMapping("/{commmentId}")
    public Comment getOneComment(@PathVariable Long commentId){
        return commentService.getOneCommentById(commentId);
    }

    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateRequest commentCreateRequest){
        return commentService.createOneComment(commentCreateRequest);
    }

    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId ,
                                    @RequestBody CommentUpdateRequest commentUpdateRequest){
        return commentService.updateOneCommentById(commentId ,commentUpdateRequest);
    }

    @DeleteMapping("/{commentId}")
    public void deleteCommentById(@PathVariable Long commentId){
        commentService.deleteCommentById(commentId);
    }
}
