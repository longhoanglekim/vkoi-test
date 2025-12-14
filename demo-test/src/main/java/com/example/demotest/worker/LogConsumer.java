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
import java.util.concurrent.TimeUnit;

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
                logEvent = logProducer.getQueue().poll(1, TimeUnit.SECONDS);
                if (logEvent != null) {
                    logBuffer.add(logEvent);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            long now = System.currentTimeMillis();
            if (logBuffer.size() >  50 || now - lastFlush > 1000) {
                flush(logBuffer);
                logBuffer.clear();
                lastFlush = now;
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
