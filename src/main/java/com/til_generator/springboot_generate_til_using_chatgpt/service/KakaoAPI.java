package com.til_generator.springboot_generate_til_using_chatgpt.service;

import com.til_generator.springboot_generate_til_using_chatgpt.dto.TistoryRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class KakaoAPI {

    @Value("${tistory.access_key}")
    private String tistoryAccessKey;

    public void writeOnBlog2(TistoryRequest tistoryRequest) throws Exception {
        URL url = new URL("https://www.tistory.com/apis/post/write?");
        String postDatta = "access_token=" + tistoryAccessKey;
        postDatta += "&output=json";
        postDatta += "&blogName=thomaschoi";
        postDatta += "&title=" + URLEncoder.encode(tistoryRequest.getTitle(), "UTF-8");
        postDatta += "&content=" + URLEncoder.encode(tistoryRequest.getContent(), "UTF-8");
        postDatta += "&category=1024642";
        postDatta += "&visibility=3";

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDatta.length()));
        System.out.println("Response Message: " + conn);
        System.out.println("response code = " + conn.getResponseCode());
        try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())){
            dos.writeBytes(postDatta);
        }
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()))){
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = bf.readLine()) != null) {
                response.append(line);
            }
            bf.close();
            System.out.println(response.toString());
        }
    }


    public String writeOnBlog(TistoryRequest tistoryRequest) {

        String reqURL = "https://www.tistory.com/apis/post/write?";
        reqURL += "access_token=" + tistoryAccessKey;
        reqURL += "&output=json";
        reqURL += "&blogName=thomaschoi";
        reqURL += "&title=" + "'" + tistoryRequest.getTitle() + "'";
        reqURL += "&content=" + "'" + tistoryRequest.getContent() + "'";
        reqURL += "&category=1024642";
        reqURL += "&visibility=20";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            int responseCode = conn.getResponseCode();
            System.out.println("Response Message: " + conn);
            System.out.println("response code = " + responseCode);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return reqURL;
    }
}
