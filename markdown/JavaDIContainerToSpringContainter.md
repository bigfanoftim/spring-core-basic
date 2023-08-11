## 변경 전

```java
public class AppConfig {

    public UserService userService() {
        return new UserServiceImpl(userRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
                userRepository(),
                discountPolicy()
        );
    }

    public UserRepository userRepository() {
        return new MemoryUserRepository();
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
```

```java
public class UserApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();

        UserService userService = appConfig.userService();

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
```

## 변경 후
```java
/**
 * Spring 구성 등록을 위한 어노테이션 추가
 */
@Configuration
public class AppConfig {

    /**
     * @Bean 어노테이션을 추가함으로써 스프링 빈에 등록된다.
     */
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
```

```java
public class UserApp {

    public static void main(String[] args) {
        /**
         * ApplicationContext를 `스프링 컨테이너`라고 한다.
         */
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
```
- 스프링 컨테이너는 `@Configuration` 어노테이션이 붙은 AppConfig를 설정 정보로 이용한다.
- 그리고 `@Bean`으로 등록된 모든 메소드를 호출하여 반환된 객체를 전부 스프링 컨테이너에 등록한다. 이때 컨테이너에 등록된 객체를 `스프링 빈`이라고 부른다.
- 순수 Java DI Container를 더 이상 이용하지 않기 때문에 `ApplicationContext로 부터 빈을 호출해서 사용`해야 한다.
- 이때 빈의 이름은 `@Bean 어노테이션이 붙은 메소드명`이다.