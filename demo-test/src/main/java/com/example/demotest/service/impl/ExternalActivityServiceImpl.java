package com.example.demotest.service.impl;

import com.example.demotest.config.ApiResponse;
import com.example.demotest.model.ActivityCount;
import com.example.demotest.service.ExternalActivityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExternalActivityServiceImpl implements ExternalActivityService {

    private final RestTemplate restTemplate;
    private final Executor activityExecutor;

    @Override
    public CompletableFuture<ActivityCount> getUserActivityCount(long userId) {

        return CompletableFuture.supplyAsync(() -> {
            try {
                ResponseEntity<ApiResponse<ActivityCount>> apiResponse =
                        restTemplate.exchange(
                                "http://localhost:8080/api/mock/external/{userId}",
                                HttpMethod.GET,
                                null,
                                new ParameterizedTypeReference<ApiResponse<ActivityCount>>() {},
                                userId
                        );

                return apiResponse.getBody().getData(); // ✅ TRẢ VỀ ActivityCount

            } catch (Exception e) {
                log.error("Call external failed, userId={}", userId, e);
                return new ActivityCount(0); // fallback
            }
        }, activityExecutor);
    }
}
