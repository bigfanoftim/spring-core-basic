package com.bigfanoftim.springcorebasic;

import com.bigfanoftim.springcorebasic.user.domain.Grade;
import com.bigfanoftim.springcorebasic.user.domain.User;
import com.bigfanoftim.springcorebasic.user.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = applicationContext.getBean("userService", UserService.class);

        User user = User.builder()
                .id(1L)
                .name("Jun")
                .grade(Grade.VIP)
                .build();

        userService.join(user);

        User findUser = userService.findUser(user.getId());

        System.out.println("findUser = " + findUser);
    }
}
