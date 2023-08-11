package com.bigfanoftim.springcorebasic.order.service;

import com.bigfanoftim.springcorebasic.user.domain.User;

public interface DiscountPolicy {

    int discount(User user, int price);
}
