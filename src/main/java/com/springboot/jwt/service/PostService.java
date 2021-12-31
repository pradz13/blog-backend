package com.springboot.jwt.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.springboot.jwt.entity.PostEntity;
import com.springboot.jwt.entity.UserEntity;
import com.springboot.jwt.repository.PostRepository;
import com.springboot.jwt.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public PostEntity showPostById(Long postId) {
        Optional<PostEntity> postEntity = postRepository.findById(postId);
        return postEntity.orElse(null);
    }

    public List<PostEntity> showPostsByPage(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<PostEntity> pagedResult = postRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }
}
