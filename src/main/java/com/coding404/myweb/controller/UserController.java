package com.coding404.myweb.controller;

import com.coding404.myweb.command.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }

    @PostMapping("/loginForm")
    public String loginForm(UserVO vo, HttpSession session) {

        // 로그인 과정은 생략 (DB 등)
        UserVO result = vo;

        if (result == null) {
            return "redirect:/user/login";
        } else {
            session.setAttribute("userVO", result);
            return "redirect:/"; // 메인으로
        }
    }

    @GetMapping("/join")
    public String join() {
        return "/user/join";
    }

    @GetMapping("/userDetail")
    public String userDetail() {
        return "/user/userDetail";
    }

}
