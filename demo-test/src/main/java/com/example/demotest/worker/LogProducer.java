package com.example.demotest.worker;


import com.example.demotest.dto.LogEvent;
import com.example.demotest.dto.NotificationRequest;
import com.example.demotest.queue.NotificationQueue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
@Slf4j
@RequiredArgsConstructor
@Getter
public class LogProducer {
    private BlockingQueue<LogEvent> queue = new LinkedBlockingQueue<>();

    public void success(LogEvent logEvent) {
        queue.offer(logEvent);
    }
    public void fail(LogEvent logEvent) {
        queue.offer(logEvent);
    }

}
