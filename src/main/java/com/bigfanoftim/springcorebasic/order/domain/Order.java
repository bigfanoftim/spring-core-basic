package com.bigfanoftim.springcorebasic.order.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Order {

    private Long userId;
    private String itemName;
    private int itemPrice;
    private int discountPrice;

    @Builder
    public Order(Long userId, String itemName, int itemPrice, int discountPrice) {
        this.userId = userId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

    public int calculatePrice() {
        return itemPrice - discountPrice;
    }
}
