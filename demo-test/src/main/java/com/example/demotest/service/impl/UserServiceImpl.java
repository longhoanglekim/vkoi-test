package com.example.demotest.service.impl;

import com.example.demotest.model.User;
import com.example.demotest.repository.UserRepository;
import com.example.demotest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

}
