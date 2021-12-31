package com.springboot.jwt.controlller;

import com.springboot.jwt.entity.PostEntity;
import com.springboot.jwt.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/add-post")
    public void addPost(@RequestBody PostEntity postEntity) {
        postService.addPost(postEntity);
    }
}
