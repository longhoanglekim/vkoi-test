package com.example.demotest.controller;

import com.example.demotest.config.ApiResponse;
import com.example.demotest.dto.NotificationRequest;
import com.example.demotest.queue.NotificationQueue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {
    private final NotificationQueue notificationQueue;
    @PostMapping("/notify")
    public ApiResponse<String> notify(@RequestBody final NotificationRequest notificationReq) {
        notificationQueue.publish(notificationReq);
        log.info("Notification has been sent to the queue");
        return ApiResponse.success("Event has added into notification queue", HttpStatus.OK);
    }
}
