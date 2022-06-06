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
                // 영문자도 아니고 숫자도 아님!
                throw new IllegalArgumentException("The username must consist of at least 3 letters, uppercase and lowercase letters (a~z, A~Z), and numbers (0~9).");
            }
        }
        return true;
    }

    public boolean checkPassword(SignupRequestDto signupRequestDto){
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        String checkpassword = signupRequestDto.getConfirm_Password();

        if(password.length() < 4)
            throw new IllegalArgumentException("It must be at least 4 characters long.");
        else if(password.contains(username))
            throw new IllegalArgumentException("The password must not contain an ID.");
        else if(!password.equals(checkpassword))
            throw new IllegalArgumentException("The passwords do not match each other.");
        else
            return true;
    }
}
