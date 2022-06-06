package com.sparta.validator;

import com.sparta.dto.PostRequestDto;
import org.springframework.stereotype.Component;

@Component
public class PostValidator {

    public static void validatePost(PostRequestDto requestDto, String username) {
        // 입력값 Validation
        if (username == null) {
            throw new IllegalArgumentException("회원 Id 가 유효하지 않습니다.");
        }

        if (requestDto.getTitle() == null || requestDto.getTitle().isEmpty()) {
            throw new IllegalArgumentException("저장할 수 있는 제목이 없습니다.");
        }

        if (requestDto.getContent() == null || requestDto.getContent().isEmpty()) {
            throw new IllegalArgumentException("저장할 수 있는 글이 없습니다.");
        }
    }
}
