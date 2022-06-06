package com.sparta.controller;


import com.sparta.model.UserRoleEnum;
import com.sparta.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 홈 디렉토리
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("username", userDetails.getUsername());

        if(userDetails.getUser().getRole() == UserRoleEnum.ADMIN)
            model.addAttribute("admin_role", true);

        return "index";
    }
}
