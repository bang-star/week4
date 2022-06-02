package com.sparta.service;

import com.sparta.dto.CommentRequestDto;
import com.sparta.model.Comment;
import com.sparta.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Long id, CommentRequestDto requestDto) {
        Comment comment = new Comment(id, requestDto);
        commentRepository.save(comment);
        return comment;
    }

    public Comment updateComment(Long id, String content) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("댓글이 존재하지 않습니다."));
        comment.setContents(content);
        commentRepository.save(comment);
        return comment;
    }

    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("댓글이 존재하지 않습니다."));

        commentRepository.delete(comment);
    }

    public List<Comment> findAllComment() {
        return commentRepository.findAll();
    }
}
