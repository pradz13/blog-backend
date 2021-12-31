package com.springboot.jwt.controlller;

import com.springboot.jwt.entity.PostEntity;
import com.springboot.jwt.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/add-post")
    public void addPost(@RequestBody PostEntity postEntity) {
        postService.addPost(postEntity);
    }

    @GetMapping("/show-posts/{postId}")
    public PostEntity showPostById(@PathVariable("postId") Long postId) {
        return postService.showPostById(postId);
    }

    @GetMapping("/show-all-posts")
    public List<PostEntity> showAllPosts() {
        return postService.showAllPosts();
    }
}
