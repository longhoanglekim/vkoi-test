package com.example.demotest.worker;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class WorkerStarter {

    private final NotificationWorker notificationWorker;
    private final RetryWorker retryWorker;
    private final LogConsumer logConsumer;

    @PostConstruct
    public void startWorkers() {
        System.out.println("Starting Worker Starter");
        notificationWorker.start();
        retryWorker.start();
        logConsumer.start();
    }
}