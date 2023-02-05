package com.til_generator.springboot_generate_til_using_chatgpt.repository;

import com.til_generator.springboot_generate_til_using_chatgpt.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByEmailAndPassword(String email, String password);

    Optional<UserEntity> findByEmail(String email);
}
