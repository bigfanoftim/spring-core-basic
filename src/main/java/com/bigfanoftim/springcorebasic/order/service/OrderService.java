package com.bigfanoftim.springcorebasic.order.service;

import com.bigfanoftim.springcorebasic.order.domain.Order;

public interface OrderService {

    Order createOrder(Long userId, String itemName, int itemPrice);
}
