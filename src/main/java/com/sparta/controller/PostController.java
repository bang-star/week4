package com.sparta.controller;

import com.sparta.dto.PostRequestDto;
import com.sparta.dto.PostUpdateRequestDto;
import com.sparta.model.Post;
import com.sparta.security.UserDetailsImpl;
import com.sparta.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("/api/post/{id}")
    public Post getPost(@PathVariable Long id){
        return postService.getPost(id);
    }

    @PostMapping("/api/post")
    public Post createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
        return postService.createPost(requestDto, username);
    }

    @PutMapping("/api/post/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto){
        return postService.updatePost(id, requestDto);
    }

    @DeleteMapping("/api/post/{id}")
    public void deletePost(@PathVariable Long id){
        postService.deletePost(id);
    }
}
