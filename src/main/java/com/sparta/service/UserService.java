package com.sparta.service;

import com.sparta.dto.LoginRequestDto;
import com.sparta.dto.SignupRequestDto;
import com.sparta.model.Users;
import com.sparta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final SignupService signupService;

    @Autowired
    public UserService(UserRepository userRepository, SignupService signupService) {
        this.userRepository = userRepository;
        this.signupService = signupService;
    }

    public void signupUser(SignupRequestDto signupRequestDto) {

        try {
            String username = signupRequestDto.getUsername();
            Optional<Users> result = userRepository.findByUsername(username);

            if (result.isPresent()) {
                throw new IllegalArgumentException("중복된 닉네임이 존재합니다.");
            }

            // 아이디 확인
            if (!signupService.usernameValidation(username)) {
                throw new IllegalArgumentException("닉네임은 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성하셔야 합니다");
            }

            // 비밀번호 확인
            if (!signupService.checkPassword(signupRequestDto)) {
                return;
            }

            String password = signupRequestDto.getPassword();

            Users user = new Users(username, password);
            userRepository.save(user);

            // return user;
        }catch (IllegalArgumentException exception){
            throw exception;
        }
    }

    public boolean loginUser(LoginRequestDto requestDto) {
        String username = requestDto.getUsername();
        Users user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("닉네임 또는 패스워드를 확인해주세요"));

        String password = requestDto.getPassword();

        return user.getPassword().equals(password) && user.getUsername().equals(username);
    }
}
