package com.bigfanoftim.springcorebasic;

import com.bigfanoftim.springcorebasic.order.service.DiscountPolicy;
import com.bigfanoftim.springcorebasic.order.service.FixDiscountPolicy;
import com.bigfanoftim.springcorebasic.order.service.OrderService;
import com.bigfanoftim.springcorebasic.order.service.OrderServiceImpl;
import com.bigfanoftim.springcorebasic.user.domain.MemoryUserRepository;
import com.bigfanoftim.springcorebasic.user.domain.UserRepository;
import com.bigfanoftim.springcorebasic.user.service.UserService;
import com.bigfanoftim.springcorebasic.user.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                userRepository(),
                discountPolicy()
        );
    }

    @Bean
    public UserRepository userRepository() {
        return new MemoryUserRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
