package com.app.quesapp.service;

import com.app.quesapp.model.Comment;
import com.app.quesapp.model.Post;
import com.app.quesapp.model.User;
import com.app.quesapp.repository.CommentRepository;
import com.app.quesapp.request.CommentCreateRequest;
import com.app.quesapp.request.CommentUpdateRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;
    private static final Logger LOG = Logger.getLogger(CommentService.class);




    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Comment> getAllCommentWithParam(Optional<Long> userId, Optional<Long> postId) {

        if (userId.isPresent() && postId.isPresent()){
            return commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }else if (userId.isPresent()){
            return commentRepository.findByUserId(userId.get());
        }else if(postId.isPresent()){
            return commentRepository.findByPostId(postId.get());

        }else{
           return   commentRepository.findAll();
        }
    }

    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest commentCreateRequest) {
        try {
            User user = userService.getOneUserById(commentCreateRequest.getUserId());
            Post post = postService.getByPostId(commentCreateRequest.getPostId());
            Comment commentToSave = new Comment();
            commentToSave.setId(commentCreateRequest.getId());
            commentToSave.setText(commentCreateRequest.getText());
            commentToSave.setUser(user);
            commentToSave.setPost(post);
            return commentRepository.save(commentToSave);
        }catch (NullPointerException e){
            LOG.error("createCommentErr" , e);
            return null;
        }

    }

    public Comment updateOneCommentById(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        try {
            Comment comment = commentRepository.getById(commentId);
            Comment commentToUpdate = comment;
            commentToUpdate.setText(commentUpdateRequest.getText());
            return commentRepository.save(commentToUpdate);
        }catch (NullPointerException e){
            LOG.error("updateCommentErr" , e);
            return null;
        }
    }

    public void deleteCommentById(Long commentId) {
        try {
            commentRepository.deleteById(commentId);
        }catch (NullPointerException e){
            LOG.error("deleteCommentErr",e);
        }
    }
}
