package com.sparta.model;

import com.sparta.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter             // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor  // 기본 생성자를 만들어줍니다.
@Entity             // DB 테이블 역할을 합니다.
public class Comment extends Timestamped{
    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id     // PK 설정
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Long postid;

    @Column(nullable = false)
    private String contents;

    public Comment(Long id, CommentRequestDto requestDto){
        this.postid = id;
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}
