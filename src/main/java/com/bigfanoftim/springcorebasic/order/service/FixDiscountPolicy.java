package com.bigfanoftim.springcorebasic.order.service;

import com.bigfanoftim.springcorebasic.user.domain.Grade;
import com.bigfanoftim.springcorebasic.user.domain.User;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;

    @Override
    public int discount(User user, int price) {
        if (user.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
