package com.bigfanoftim.springcorebasic.user.service;

import com.bigfanoftim.springcorebasic.user.domain.User;

public interface UserService {

    void join(User user);

    User findUser(Long userId);
}
