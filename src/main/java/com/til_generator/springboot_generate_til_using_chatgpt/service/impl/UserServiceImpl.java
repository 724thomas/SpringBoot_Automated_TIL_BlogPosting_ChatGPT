package com.til_generator.springboot_generate_til_using_chatgpt.service.impl;

import com.til_generator.springboot_generate_til_using_chatgpt.dto.UserRequest;
import com.til_generator.springboot_generate_til_using_chatgpt.repository.UserRepository;
import com.til_generator.springboot_generate_til_using_chatgpt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String insertUsers(UserRequest userRequest) {
        try {
            userRepository.save(userRequest.toEntity());
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }

    @Override
    public String loginUsers(UserRequest userRequest) {
        try {
            userRepository.findByEmailAndPassword(userRequest.getEmail(), userRequest.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }

}
