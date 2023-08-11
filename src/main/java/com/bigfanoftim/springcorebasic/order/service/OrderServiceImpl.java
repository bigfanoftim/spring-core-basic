package com.bigfanoftim.springcorebasic.order.service;

import com.bigfanoftim.springcorebasic.order.domain.Order;
import com.bigfanoftim.springcorebasic.user.domain.User;
import com.bigfanoftim.springcorebasic.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long userId, String itemName, int itemPrice) {
        User user = userRepository.findById(userId);
        int discountPrice = discountPolicy.discount(user, itemPrice);

        return Order.builder()
                .userId(user.getId())
                .itemName(itemName)
                .itemPrice(itemPrice)
                .discountPrice(discountPrice)
                .build();
    }
}
