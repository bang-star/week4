package com.sparta.controller;

import com.sparta.dto.PostRequestDto;
import com.sparta.model.Post;
import com.sparta.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/api/posts")
    public List<Post> getPosts() {
        return postService.findAll();
    }

    @PostMapping("/api/post")
    public Post createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    @PutMapping("/api/post/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        return postService.updatePost(id, requestDto.getContent());
    }

    @DeleteMapping("/api/post/{id}")
    public void deletePost(@PathVariable Long id){
        postService.deletePost(id);
    }
}
