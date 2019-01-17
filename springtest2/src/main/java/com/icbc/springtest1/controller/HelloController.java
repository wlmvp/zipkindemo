package com.icbc.springtest1.controller;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.ServerTracer;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@EnableAutoConfiguration
public class HelloController {

    @Autowired
    private CloseableHttpClient httpClient;
    @Autowired
    private Brave brave;

    private Random random = new Random();


    @RequestMapping("/service2")
    private String service() throws Exception {

        int sleep = random.nextInt(1000);
        TimeUnit.MILLISECONDS.sleep(sleep);
        HttpGet get = new HttpGet("http://localhost:8083/service3");
        CloseableHttpResponse response = httpClient.execute(get);
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        get = new HttpGet("http://localhost:8084/service4");
        response = httpClient.execute(get);
        result += EntityUtils.toString(response.getEntity(), "utf-8");

        ServerTracer trace = brave.serverTracer();
        return "[service2]:" + result;
    }
}