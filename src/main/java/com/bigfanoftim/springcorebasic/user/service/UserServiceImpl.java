package com.bigfanoftim.springcorebasic.user.service;

import com.bigfanoftim.springcorebasic.user.domain.User;
import com.bigfanoftim.springcorebasic.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void join(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUser(Long userId) {
        return userRepository.findById(userId);
    }
}
