package com.sparta.service;

import com.sparta.dto.PostRequestDto;
import com.sparta.model.Post;
import com.sparta.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return post;
    }

    public Post updatePost(Long id, String content) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 게시글이 존재하지 않습니다."));

        post.setContent(content);
        postRepository.save(post);
        return post;
    }

    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 게시글이 존재하지 않습니다."));

        postRepository.deleteById(id);
    }
}
