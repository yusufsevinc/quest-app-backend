package com.app.quesapp.service;

import com.app.quesapp.model.Like;
import com.app.quesapp.model.Post;
import com.app.quesapp.model.User;
import com.app.quesapp.repository.LikeRepository;
import com.app.quesapp.request.LikeCreateRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;

    private final static Logger LOG = Logger.getLogger(LikeService.class);

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }


    public List<Like> getAllLikeWithParam(Optional<Long> userId, Optional<Long> postId) {

        if (userId.isPresent() && postId.isPresent()){
            return likeRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }else if (userId.isPresent()){
            return likeRepository.findByUserId(userId.get());
        }else if (postId.isPresent()){
            return likeRepository.findByPostId(postId.get());
        }else {
            return null;
        }
    }

    public Like getOneLikeById(Long likeId) {
            return likeRepository.findById(likeId).orElseThrow(() -> new NullPointerException("Not found like Id"));
    }

    public Like createOneLike(LikeCreateRequest likeCreateRequest) {
        try {
            User user = userService.getOneUserById(likeCreateRequest.getUserId());
            Post post = postService.getByPostId(likeCreateRequest.getPostId());

            Like likeToSave = new Like();
            likeToSave.setId(likeCreateRequest.getId());
            likeToSave.setUser(user);
            likeToSave.setPost(post);
            return likeRepository.save(likeToSave);
        }catch (NullPointerException e){
            LOG.error("createOneLikeErr",e);
            return null;
        }
    }

    public void deleteLikeById(Long likeId) {
        try {
            likeRepository.deleteById(likeId);
        }catch (NullPointerException e){
            LOG.error("deleteLikeErr",e);
        }
    }
}
