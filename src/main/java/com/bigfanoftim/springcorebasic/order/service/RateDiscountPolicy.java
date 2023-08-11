package com.bigfanoftim.springcorebasic.order.service;

import com.bigfanoftim.springcorebasic.user.domain.Grade;
import com.bigfanoftim.springcorebasic.user.domain.User;

public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(User user, int price) {
        if (user.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
