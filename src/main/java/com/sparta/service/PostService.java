package com.sparta.service;

import com.sparta.dto.PostRequestDto;
import com.sparta.dto.PostUpdateRequestDto;
import com.sparta.model.Post;
import com.sparta.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        LocalDateTime start = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(0,0,0));
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));
        return postRepository.findAllByModifiedAtBetweenOrderByModifiedAtDesc(start, end);
    }

    public Post createPost(PostRequestDto requestDto, String username) {
        Post post = new Post(requestDto, username);
        postRepository.save(post);
        return post;
    }

    public Post updatePost(Long id, PostUpdateRequestDto requestDto) {
        String content = requestDto.getContent();

        if(content.isEmpty()){
            throw new IllegalArgumentException("내용이 존재하지 않습니다.");
        }

        Post post = getPost(id);

        post.setContent(content);
        postRepository.save(post);
        return post;
    }

    public void deletePost(Long id) {
        Post post = getPost(id);

        postRepository.deleteById(id);
    }

    public Post getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 게시글이 존재하지 않습니다."));
    }
}
