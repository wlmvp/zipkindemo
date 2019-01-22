package com.wl.springtest1.controller;

import brave.Tracing;
import brave.propagation.TraceContext;
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

    @Autowired
    Tracing tracing;

    private Random random = new Random();

    @RequestMapping("/service1")
    private String service() throws Exception {

        TraceContext context = tracing.currentTraceContext().get();
        long aaaa = context.traceId();

//        LoggerBuilder loggerBuilder =new LoggerBuilder();
//        Logger logger = loggerBuilder.getLogger("test");

        int sleep = random.nextInt(1000);
        TimeUnit.MILLISECONDS.sleep(sleep);
        //HttpGet get = new HttpGet("http://localhost:8082/service2");

        LOG.info("get http://localhost:8082/service2,sleep:{}",sleep);
//        logger.info("get11 http://localhost:8082/service2,sleep:{}",sleep);
        String data = restTemplate.getForObject("http://localhost:8082/service2", String.class);
        TraceContext context1 = tracing.currentTraceContext().get();
        long aaaa1 = context1.traceId();
        return "[service1]:" + data;
//        CloseableHttpResponse response = httpClient.execute(get);
//        return "[service1]:" + EntityUtils.toString(response.getEntity(), "utf-8");
    }
}