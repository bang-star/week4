package com.sparta.service;

import com.sparta.dto.SignupRequestDto;
import org.springframework.stereotype.Controller;

@Controller
public class SignupService {

    public boolean usernameValidation(String username) {
        char ch;
        if(username.length() < 3)
            return  false;

        for (int i = 0; i < username.length(); i++) {
            ch = username.charAt(i);

            if (ch >= 0x61 && ch <= 0x7A) {             // 소문자
                continue;
            } else if (ch >= 0x41 && ch <= 0x5A) {      // 대문자
                continue;
            } else if (ch >= 0x30 && ch <= 0x39) {      // 숫자
                continue;
            } else {
                return false;                           // 영문자도 아니고 숫자도 아님!
            }
        }
        return true;
    }

    public boolean checkPassword(SignupRequestDto signupRequestDto){
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        String checkpassword = signupRequestDto.getConfirm_Password();

        if(password.length() < 4)
            throw new IllegalArgumentException("비밀번호는 최소 4글자 이상이어야 합니다.");
        else if(password.contains(username))
            throw new IllegalArgumentException("비밀번호에 아이디가 포함되면 안됩니다.");
        else if(!password.equals(checkpassword))
            throw new IllegalArgumentException("비밀번호가 서로 일치하지 않습니다.");
        else
            return true;
    }
}
