package com.app.quesapp.controller;

import com.app.quesapp.model.Like;
import com.app.quesapp.request.LikeCreateRequest;
import com.app.quesapp.service.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;

    }

    @GetMapping
    public List<Like> getAllLike(@RequestParam Optional<Long> userId ,
                                 @RequestParam Optional<Long> postId){
       return likeService.getAllLikeWithParam(userId , postId);
    }

    @GetMapping("/{likeId}")
    public Like getOneLikeById(Long likeId){
        return likeService.getOneLikeById(likeId);
    }

    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateRequest likeCreateRequest){
        return likeService.createOneLike(likeCreateRequest);
    }

    @DeleteMapping("/{likeId}")
    public void deleteLikeById(@PathVariable Long likeId){
        likeService.deleteLikeById(likeId);
    }

}
