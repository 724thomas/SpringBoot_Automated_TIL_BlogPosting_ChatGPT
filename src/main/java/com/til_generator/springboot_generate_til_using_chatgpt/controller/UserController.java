package com.til_generator.springboot_generate_til_using_chatgpt.controller;

import com.til_generator.springboot_generate_til_using_chatgpt.dto.UserRequest;
import com.til_generator.springboot_generate_til_using_chatgpt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String insertUsers(UserRequest userRequest) {
        userService.insertUsers(userRequest);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String loginUser(UserRequest request, HttpSession session) {
        if (userService.loginUsers(request).equals ("success")) {
            session.setAttribute("user", request.getEmail());
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }
}
