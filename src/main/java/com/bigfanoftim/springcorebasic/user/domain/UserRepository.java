package com.bigfanoftim.springcorebasic.user.domain;

public interface UserRepository {

    void save(User user);

    User findById(Long userId);
}
