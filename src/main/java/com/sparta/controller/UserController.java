package com.sparta.controller;

import com.sparta.dto.LoginRequestDto;
import com.sparta.dto.SignupRequestDto;
import com.sparta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원가입 페이지 요청
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원가입 요청
    @PostMapping("/user/signup")
    public String signupUser(@RequestBody SignupRequestDto requestDto) {
        userService.signupUser(requestDto);
        return "redirect:/user/login";
    }

    // 로그인 요청
    @PostMapping("/user/login")
    public String loginUser(@RequestBody LoginRequestDto requestDto){
        return userService.loginUser(requestDto) ? "redirect:/" : "redirect:/login";

    }
}
