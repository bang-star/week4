package com.sparta.service;

import com.sparta.dto.LoginRequestDto;
import com.sparta.dto.SignupRequestDto;
import com.sparta.model.Users;
import com.sparta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final SignupService signupService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, SignupService signupService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.signupService = signupService;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입
    public void signupUser(SignupRequestDto requestDto) {

        try {
            String username = requestDto.getUsername();
            Optional<Users> result = userRepository.findByUsername(username);

            if (result.isPresent()) {
                throw new IllegalArgumentException("중복된 닉네임이 존재합니다.");
            }

            // 아이디 확인
            if (!signupService.usernameValidation(username)) {
                throw new IllegalArgumentException("닉네임은 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성하셔야 합니다");
            }

            // 비밀번호 확인
            if (!signupService.checkPassword(requestDto)) {
                return;
            }

            String password = passwordEncoder.encode(requestDto.getPassword());

            Users user = new Users(username, password);
            userRepository.save(user);
        }catch (IllegalArgumentException exception){
            throw exception;
        }
    }

//    public boolean loginUser(LoginRequestDto requestDto) {
//        try{
//            String username = requestDto.getUsername();
//            Users user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("닉네임 또는 패스워드를 확인해주세요"));
//
//            String password = requestDto.getPassword();
//
//            return user.getPassword().equals(password) && user.getUsername().equals(username);
//        }catch (Exception ex){
//            throw ex;
//        }
//    }
}
