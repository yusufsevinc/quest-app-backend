package com.app.quesapp.controller;

import com.app.quesapp.model.Post;
import com.app.quesapp.request.PostCreateRequest;
import com.app.quesapp.request.PostUpdateRequest;
import com.app.quesapp.response.PostResponse;
import com.app.quesapp.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId){
        return postService.getAllPosts(userId);
    }

    @PostMapping
    public Post save(@RequestBody PostCreateRequest newPostCreateRequest){
        return postService.save(newPostCreateRequest);
    }
    @GetMapping("/{postId}")
    public Post getByPost(@PathVariable Long postId){
        return postService.getByPostId(postId);
    }
    @PutMapping("/{postId}")
    public Post updatePostById(@PathVariable Long postId , @RequestBody PostUpdateRequest postUpdateRequest){
        return postService.updatePostById(postId , postUpdateRequest);
    }

    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId ){
        postService.deleteById(postId);
    }

}
