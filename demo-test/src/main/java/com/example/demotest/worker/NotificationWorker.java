package com.example.demotest.worker;

import com.example.demotest.dto.LogEvent;
import com.example.demotest.dto.NotificationRequest;
import com.example.demotest.exception.BusinessException;
import com.example.demotest.queue.NotificationQueue;
import com.example.demotest.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationWorker {
    private final NotificationQueue notificationQueue;
    private final EmailService emailService;
    private final LogProducer logProducer;
    @Async
    public void start() {
        System.out.println("Worker start!");
        while (true) {
            try {
                NotificationRequest notificationRequest = notificationQueue.take();
                handleEvent(notificationRequest);
            } catch (InterruptedException e) {
                log.error("Worker error : " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
    private void handleEvent(NotificationRequest notificationRequest) {
        try {
            emailService.sendEmail(notificationRequest);
            LogEvent logEvent = new LogEvent();
            logEvent.setOrderId(notificationRequest.getOrderId());
            logEvent.setResult("SUCCESS");
            logEvent.setMessage("Mail sent successfully");
            logProducer.success(logEvent);
        } catch (Exception e) {
            LogEvent logEvent = new LogEvent();
            logEvent.setOrderId(notificationRequest.getOrderId());
            logEvent.setResult("FAILURE");
            logEvent.setMessage("Mail sent failure");
            logEvent.setOrderId(notificationRequest.getOrderId());
            logProducer.fail(logEvent);
            if (notificationRequest.getRetryCount() < 3) {
                notificationRequest.incrementRetryCount();
                notificationQueue.retry(notificationRequest);
            } else {
                notificationQueue.sendToDLQ(notificationRequest);
            }
         }
    }
}
