package com.til_generator.springboot_generate_til_using_chatgpt.service;

import com.til_generator.springboot_generate_til_using_chatgpt.dto.TistoryRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

@Service
public class KakaoAPI {

    @Value("${tistory.access_key}")
    private String tistoryAccessKey;

    public void writeOnBlog2(TistoryRequest tistoryRequest) throws IOException {
        URL url = new URL("https://www.tistory.com/apis/post/write");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestMethod("POST"); // PUT is another valid option
        http.setDoOutput(true);

        Map<String,String> arguments = new HashMap<>();
        arguments.put("access_token", tistoryAccessKey);
        arguments.put("output", "json");
        arguments.put("blogName", "thomaschoi");
        arguments.put("title", tistoryRequest.getTitle());
        arguments.put("content", tistoryRequest.getContent());
        arguments.put("category", "1024642");
        arguments.put("visibility", "3");
        System.out.println("----------------------------------------------------------");
        System.out.println(arguments);
        System.out.println("----------------------------------------------------------");

        StringJoiner sj = new StringJoiner("&");
        for(Map.Entry<String,String> entry : arguments.entrySet())
            sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                    + URLEncoder.encode(entry.getValue(), "UTF-8"));
        byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        http.connect();
        try(OutputStream os = http.getOutputStream()) {
            os.write(out);
        }
    }

}
