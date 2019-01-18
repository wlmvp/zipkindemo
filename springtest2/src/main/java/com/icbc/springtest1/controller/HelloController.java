package com.icbc.springtest1.controller;

import com.github.kristofa.brave.Brave;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@EnableAutoConfiguration
public class HelloController {

    private final static Logger LOG = LoggerFactory.getLogger(HelloController.class);

    //    @Autowired
//    private CloseableHttpClient httpClient;
    @Autowired
    RestTemplate restTemplate;

    private Random random = new Random();


    @RequestMapping("/service2")
    private String service() throws Exception {

        int sleep = random.nextInt(1000);
        TimeUnit.MILLISECONDS.sleep(sleep);
//        HttpGet get = new HttpGet("http://localhost:8083/service3");
//        LOG.info("get http://localhost:8082/service3,sleep:{}",sleep);
//        CloseableHttpResponse response = httpClient.execute(get);
//        String result = EntityUtils.toString(response.getEntity(), "utf-8");
//        get = new HttpGet("http://localhost:8084/service4");
//        response = httpClient.execute(get);
//        result += EntityUtils.toString(response.getEntity(), "utf-8");
//        return "[service2]:" + result;


        LOG.info("get http://localhost:8083/service3,sleep:{}",sleep);
        String data1 = restTemplate.getForObject("http://localhost:8083/service3", String.class);
        LOG.info("get http://localhost:8084/service3,sleep:{}",sleep);
        String data2 = restTemplate.getForObject("http://localhost:8084/service4", String.class);
        return "[service1]:" + data1 + data2;
    }
}