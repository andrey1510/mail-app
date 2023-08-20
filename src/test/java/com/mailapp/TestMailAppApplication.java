package com.mailapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestMailAppApplication {

    public static void main(String[] args) {
        SpringApplication.from(MailAppApplication::main).with(TestMailAppApplication.class).run(args);
    }

}
