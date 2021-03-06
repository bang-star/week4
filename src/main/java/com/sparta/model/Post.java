package com.sparta.model;

import com.sparta.dto.PostRequestDto;
import com.sparta.validator.PostValidator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter             // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor  // 기본 생성자를 만들어줍니다.
@Entity             // DB 테이블 역할을 합니다.
public class Post extends Timestamped{
    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id     // PK 설정
    private Long id;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    public Post(PostRequestDto requestDto, String username){

        PostValidator.validatePost(requestDto, username);

        this.username = username;
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
