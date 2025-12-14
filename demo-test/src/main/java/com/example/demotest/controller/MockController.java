package com.example.demotest.controller;

import com.example.demotest.config.ApiResponse;
import com.example.demotest.model.ActivityCount;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.HTML;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mock/external/{userId}")
public class MockController {
    @GetMapping
    public ApiResponse<?> mockExternal(@PathVariable long userId) throws InterruptedException {
        int delay = new Random().nextInt(3000) + 2000;
        Thread.sleep(delay);
        Map<Long, Integer> map = Map.of(
                1L,  42,
                2L, 31,
                3L, 17
                );
        if (map.containsKey(userId)) {
            return ApiResponse.success(new ActivityCount(map.get(userId)), "Successfully get user;s activity", HttpStatus.FOUND);
        }
        return ApiResponse.success(new ActivityCount(0), "Cannot get this user's activity", HttpStatus.NOT_FOUND);
    }
}
