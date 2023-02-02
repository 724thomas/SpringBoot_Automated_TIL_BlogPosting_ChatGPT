package com.til_generator.springboot_generate_til_using_chatgpt.page.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.til_generator.springboot_generate_til_using_chatgpt.dto.ChatGptRequest;
import com.til_generator.springboot_generate_til_using_chatgpt.dto.ChatGptResponse;
import com.til_generator.springboot_generate_til_using_chatgpt.dto.FormInputDTO;
import com.til_generator.springboot_generate_til_using_chatgpt.service.ChatGptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {

    @Autowired
    private ObjectMapper jsonMapper;
    @Autowired private ChatGptService client;

    private String chatWithGpt3(String message) throws Exception {
        var completion = ChatGptRequest.defaultWith(message);
        var postBodyJson = jsonMapper.writeValueAsString(completion);
        var responseBody = client.postToOpenAiApi(postBodyJson, ChatGptService.OpenAiService.GPT_3);
        var completionResponse = jsonMapper.readValue(responseBody, ChatGptResponse.class);
        return completionResponse.firstAnswer().orElseThrow();
    }

    @GetMapping(path = "/")
    public String index() {
        return "index";
    }

    @PostMapping(path = "/")
    public String chat(Model model, @ModelAttribute FormInputDTO dto) {
        try {
            String temp = chatWithGpt3(dto.prompt());
            model.addAttribute("request", dto.prompt());
            model.addAttribute("response", temp);
            System.out.println(temp);
        } catch (Exception e) {
            model.addAttribute("response", "Error in communication with OpenAI ChatGPT API.");
        }
        return "index";
    }

}
