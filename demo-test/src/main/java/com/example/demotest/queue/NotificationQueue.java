package com.example.demotest.queue;

import com.example.demotest.dto.NotificationRequest;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class NotificationQueue {
    private BlockingQueue<NotificationRequest> notificationQueue = new LinkedBlockingQueue<>();
    private BlockingQueue<NotificationRequest> retryNotificationQueue = new LinkedBlockingQueue<>();
    private BlockingQueue<NotificationRequest> dlqNotificationQueue =  new LinkedBlockingQueue<>();

    public void publish(NotificationRequest notificationReq) {
        notificationQueue.offer(notificationReq);
    }

    public NotificationRequest take() throws InterruptedException {
        return notificationQueue.take();
    }

    public void retry(NotificationRequest notificationReq) {
        retryNotificationQueue.offer(notificationReq);
    }

    public NotificationRequest retryTake() throws InterruptedException {
        return retryNotificationQueue.take();
    }

    public void sendToDLQ(NotificationRequest notificationReq) {
        dlqNotificationQueue.offer(notificationReq);
        log.error("sendToDLQ orderId={}", notificationReq.getOrderId());
    }
}
