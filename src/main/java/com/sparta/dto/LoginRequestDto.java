package com.sparta.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDto {
    private Long cid;
    private String username;
    private String password;
}
