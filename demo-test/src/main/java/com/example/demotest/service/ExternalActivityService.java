package com.example.demotest.service;

import com.example.demotest.model.ActivityCount;

import java.util.concurrent.CompletableFuture;

public interface ExternalActivityService {
    public CompletableFuture<ActivityCount> getUserActivityCount(long userId);
}
