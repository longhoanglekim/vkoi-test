package com.example.demotest.worker;

import com.example.demotest.dto.LogEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogConsumer {
    private final LogProducer logProducer;

    @Async
    public void start() {
        System.out.println("Log consumer start!");
        List<LogEvent> logBuffer = new ArrayList<>();
        long lastFlush = System.currentTimeMillis();
        while (true) {
            LogEvent logEvent;
            try {
                logEvent = logProducer.getQueue().take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            logBuffer.add(logEvent);
            long now = System.currentTimeMillis();
            if (logBuffer.size() >  50 || now - lastFlush > 5000) {
                flush(logBuffer);
                logBuffer.clear();
                lastFlush = now;
                System.out.println("Log consumer has been sent to the queue");
            } else {
                System.out.println("Nothing to flush");
            }
        }
    }

    void flush(List<LogEvent> logEvents) {
        for (LogEvent logEvent : logEvents) {
            log.info("orderId={} result= {} message={}",
                    logEvent.getOrderId(),
                    logEvent.getResult(),
                    logEvent.getMessage());
        }
    }
}
