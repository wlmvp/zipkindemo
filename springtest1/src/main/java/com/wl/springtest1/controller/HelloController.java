package com.wl.springtest1.controller;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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

    private Random random = new Random();

    @RequestMapping("/service1")
    private String service() throws Exception {
        int sleep= random.nextInt(1000);
        TimeUnit.MILLISECONDS.sleep(sleep);
        HttpGet get = new HttpGet("http://localhost:8082/service2");
        CloseableHttpResponse response = httpClient.execute(get);
        return "[service1]:"+ EntityUtils.toString(response.getEntity(), "utf-8");
    }
}