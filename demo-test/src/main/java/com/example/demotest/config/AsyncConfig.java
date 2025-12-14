package com.example.demotest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class AsyncConfig {
    @Bean
    public Executor activityExecutor() {
        return Executors.newFixedThreadPool(20);
    }
}
