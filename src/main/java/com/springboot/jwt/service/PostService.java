package com.springboot.jwt.service;

import java.time.LocalDateTime;

import com.springboot.jwt.entity.PostEntity;
import com.springboot.jwt.entity.UserEntity;
import com.springboot.jwt.repository.PostRepository;
import com.springboot.jwt.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public void addPost(PostEntity postEntity) {
        log.info("Adding the post : {}", postEntity.getPostTitle());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userRepository.findByUserName(authentication.getName());
        postEntity.setUserEntity(userEntity);
        postEntity.setCreatedDate(LocalDateTime.now());
        String postBody = postEntity.getPostBody().replaceAll("(\r\n|\n)", "<br />");
        postEntity.setPostBody(postBody);
        postRepository.save(postEntity);
        log.info("Successfully added the post : {}", postEntity.getPostTitle());
    }
}
