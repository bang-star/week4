package com.sparta.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentRequestDto {
    private Long pid;
    private String username;
    private String contents;
}
