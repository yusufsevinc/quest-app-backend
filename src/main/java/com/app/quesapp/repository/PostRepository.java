package com.app.quesapp.repository;

import com.app.quesapp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByUserId(Optional<Long> userId);
}
