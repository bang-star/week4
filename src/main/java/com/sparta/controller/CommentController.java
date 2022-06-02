package com.sparta.controller;

import com.sparta.dto.CommentRequestDto;
import com.sparta.model.Comment;
import com.sparta.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }
    @GetMapping("/api/comments")
    public List<Comment> findAllComment(){
        return commentService.findAllComment();
    }

    @PostMapping("/api/comment/{id}")
    public Comment createComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto){
        return commentService.createComment(id, requestDto);    // 게시글 id
    }

    @PutMapping("/api/comment/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto){
        return commentService.updateComment(id, requestDto.getContents());       // 댓글 id
    }

    @DeleteMapping("/api/comment/{id}")
    public void deleteComment(@PathVariable Long id){           // 댓글 id
        commentService.deleteComment(id);
    }
}
