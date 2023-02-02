package com.til_generator.springboot_generate_til_using_chatgpt.page.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.til_generator.springboot_generate_til_using_chatgpt.dto.ChatGptRequest;
import com.til_generator.springboot_generate_til_using_chatgpt.dto.ChatGptResponse;
import com.til_generator.springboot_generate_til_using_chatgpt.dto.FormInputDTO;
import com.til_generator.springboot_generate_til_using_chatgpt.dto.TistoryRequest;
import com.til_generator.springboot_generate_til_using_chatgpt.service.ChatGptService;
import com.til_generator.springboot_generate_til_using_chatgpt.service.KakaoAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {

    @Autowired
    private KakaoAPI kakaoAPI;
    @Autowired
    private ObjectMapper jsonMapper;
    @Autowired
    private ChatGptService client;

    @Autowired
    private static String tempRequest;
    @Autowired
    private static String tempResponse;

    private String chatWithGpt3(String message) throws Exception {
        ChatGptRequest completion = ChatGptRequest.defaultWith(message);
        String postBodyJson = jsonMapper.writeValueAsString(completion);
        String responseBody = client.postToOpenAiApi(postBodyJson, ChatGptService.OpenAiService.GPT_3);
        ChatGptResponse completionResponse = jsonMapper.readValue(responseBody, ChatGptResponse.class);
        return completionResponse.firstAnswer().orElseThrow();
    }

    @GetMapping(path = "/")
    public String index() {
        return "index";
    }

    @PostMapping(path = "/")
    public String chat(Model model, @ModelAttribute FormInputDTO dto) {
        try {
            tempRequest = dto.prompt();
            tempResponse = chatWithGpt3(tempRequest);
            model.addAttribute("request", tempRequest);
            model.addAttribute("response", tempResponse);
            System.out.println(tempRequest);
            System.out.println(tempResponse);

            tempRequest = tempRequest.replace("\n","").replace(" ", "%20").trim();
            tempResponse = tempResponse.replace("\n","").replace(" ", "%20").trim();

        } catch (Exception e) {
            model.addAttribute("response", "Error in communication with OpenAI ChatGPT API.");
        }
        return "index";
    }


    @GetMapping("/postOnBlog")
    public String postOnBlog() throws Exception {
        TistoryRequest tistoryRequest = new TistoryRequest(tempRequest, tempResponse);
        kakaoAPI.writeOnBlog(tistoryRequest);
        return "index";
    }
}
