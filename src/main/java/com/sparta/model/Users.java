package com.sparta.model;

import com.sparta.dto.SignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter             // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor  // 기본 생성자를 만들어줍니다.
@Entity             // DB 테이블 역할을 합니다.
public class Users {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id     // PK 설정
    private Long id;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)        // 서버에서는 Enum, DB에서는 String(변환작업은 Spring이 Enumrated에 의해서 해준다.)
    private UserRoleEnum role;

    public Users(String username, String password, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}

