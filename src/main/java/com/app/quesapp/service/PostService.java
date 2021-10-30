package com.app.quesapp.service;

import com.app.quesapp.model.Post;
import com.app.quesapp.model.User;
import com.app.quesapp.repository.PostRepository;
import com.app.quesapp.request.PostCreateRequest;
import com.app.quesapp.request.PostUpdateRequest;
import com.app.quesapp.response.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository postRepository;
    private UserService userService;

    public PostService(PostRepository postRepository,UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> listPost;
        if (userId.isPresent()){
            listPost = postRepository.findByUserId(userId);
        }else {
            listPost = postRepository.findAll();
        }
       return listPost.stream().map(post -> new PostResponse(post)).collect(Collectors.toList());
    }

    public Post getByPostId(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post save(PostCreateRequest newPostCreateRequest) {
        User user = userService.getOneUserById(newPostCreateRequest.getUserId());
        if (user == null){
            return null;
        }else {
            Post toSave = new Post();
            toSave.setId(newPostCreateRequest.getId());
            toSave.setText(newPostCreateRequest.getText());
            toSave.setTitle(newPostCreateRequest.getTitle());
            toSave.setUser(user);
            return postRepository.save(toSave);
        }
    }

    public void deleteById(Long postId) {
        postRepository.deleteById(postId);
    }

    public Post updatePostById(Long postId, PostUpdateRequest postUpdateRequest) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()){
            Post toUpdate = post.get();
            toUpdate.setText(postUpdateRequest.getText());
            toUpdate.setTitle(postUpdateRequest.getTitle());
            return postRepository.save(toUpdate);
        }
        return null;
    }
}
