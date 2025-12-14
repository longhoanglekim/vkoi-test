package com.example.demotest.worker;


import com.example.demotest.dto.NotificationRequest;
import com.example.demotest.queue.NotificationQueue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RetryWorker {
    private final NotificationQueue notificationQueue;

    @Async
    public void start() {
        System.out.println("Retry Worker start!");
        while (true) {
            try {
                NotificationRequest notificationRequest = notificationQueue.retryTake();
                System.out.println("Retrying...");
                notificationQueue.publish(notificationRequest);
            } catch (Exception e) {
                log.error("Retry error : " + e.getMessage());
            }
        }
    }
}
