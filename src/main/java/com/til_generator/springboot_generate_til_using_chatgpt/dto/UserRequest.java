package com.til_generator.springboot_generate_til_using_chatgpt.dto;


import com.til_generator.springboot_generate_til_using_chatgpt.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.LazyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class UserRequest {
    private String email;
    private String password;
    private String tistoryCode;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .email(this.email)
                .password(this.password)
                .tistoryCode(this.tistoryCode)
                .build();
    }
}
