package com.til_generator.springboot_generate_til_using_chatgpt.service;

import com.til_generator.springboot_generate_til_using_chatgpt.dto.UserRequest;

import javax.servlet.http.HttpSession;

public interface UserService {

    String insertUsers(UserRequest userRequest);

    String loginUsers(UserRequest userRequest);
}
