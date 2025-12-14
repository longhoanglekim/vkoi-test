package com.example.demotest.service.impl;

import com.example.demotest.dto.UserReport;
import com.example.demotest.model.ActivityCount;
import com.example.demotest.model.User;
import com.example.demotest.service.ExternalActivityService;
import com.example.demotest.service.ReportService;
import com.example.demotest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService {
    private final UserService userService;
    private final ExternalActivityService externalActivityService;
    @Override
    public List<UserReport> getUserReport() {
        List<User> userList = userService.findAllUser();
        Map<Long, CompletableFuture<ActivityCount>> map = userList
                .stream().collect(Collectors.toMap(User::getId,
                        user ->
                                externalActivityService.getUserActivityCount(user.getId())
                                        .orTimeout(5, TimeUnit.SECONDS)
                                        .whenComplete((result, ex) -> {
                                            if (ex != null) {
                                                log.error("Future FAILED for userId={}", user.getId(), ex);
                                            } else {
                                                log.debug("Future DONE for userId={}, result={}", user.getId(), result);
                                            }
                                        })
                                        .exceptionally(ex -> new ActivityCount(0))
                ));
        CompletableFuture.allOf(map.values().toArray(new CompletableFuture[0])).join();

        return userList.stream()
                .map(u -> {
                    ActivityCount activityCount = map.get(u.getId()).join();
                    return UserReport.builder()
                            .userId(u.getId())
                            .name(u.getName())
                            .lastLogin(u.getLastLogin())
                            .activityCount(activityCount.getActivity_count())
                            .build();
                }
        ).toList();
    }

}
