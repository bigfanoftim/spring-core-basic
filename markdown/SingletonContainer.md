# @Configuration, Singleton

```java
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
```

현재 위와 같이 Java Class로 설정을 진행하고 있다. 근데 코드를 잘 살펴보면 `userRepository()` 메소드가 여러번 호출되는 것을 볼 수 있다.

이렇다면 메소드가 호출될 때마다 새로운 `MemoryUserRepository` 객체를 반환한게 아닐까?

하지만 막상 테스트 코드를 통해 검증해보면 userService와 orderService의 userRepository 참조가 같은 것을 확인할 수 있다.

이게 가능한 이유는 스프링 내부적으로 바이트코드를 조작하는 CGLIB 라이브러리를 사용하기 때문이다.

```java
@Test
void configurationDeep() {
    ApplicationContext ac = new
    AnnotationConfigApplicationContext(AppConfig.class);
    
    AppConfig bean = ac.getBean(AppConfig.class);
    
    System.out.println(bean.getClass());
    //출력: class hello.core.AppConfig$$EnhancerBySpringCGLIB$$bd479d70 }
```

위의 테스트 코드를 실행해보면 실제로 AppConfig 클래스가 빈으로 등록된게 아니라 CGLIB를 통해 새롭게 구현된 객체가 빈으로 등록된 것을 확인할 수 있다.

스프링이 내부적으로 AppConfig 클래스를 상속받은 또 다른 클래스를 빈으로 등록한 것이다.

이 싱글톤을 구현하는 모든 동작은 `@Configuration` 어노테이션을 사용해야 하기 떄문에 꼭 잊지 말고 추가하자.  

## 정리

- `@Configuration` 어노테이션을 통해 등록된 설정 클래스의 Bean들은 내부적으로 바이트코드 조작 라이브러리를 사용하여 싱글톤으로 관리된다.
- 스프링 설정 정보는 항상 `@Configuration`을 사용하자.