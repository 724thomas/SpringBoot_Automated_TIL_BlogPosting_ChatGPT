package com.til_generator.springboot_generate_til_using_chatgpt.dto;

public record ChatGptRequest(String model, String prompt, double temperature, int max_tokens) {
        public static ChatGptRequest defaultWith(String prompt) {
            return new ChatGptRequest("text-davinci-003", prompt, 0.7, 4000);
        }
}