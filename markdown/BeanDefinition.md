# 다양한 형식으로 설정 정보를 처리하는 방법, BeanDefinition

스프링은 Java Class를 만들어 설정하는 것뿐만 아니라 XML, Groovy 등 다양한 형식의 설정 정보를 처리할 수 있게끔 유연하게 설계되어 있다.

이렇게 다양한 형식을 지원할 수 있는 이유는 바로 추상화이다.

스프링 컨테이너는 `BeanDefinition`이라는 인터페이스에만 의존하고 있으니 어떤 형식이던지 BeanDefinition의 구현체만 만들어 낼 수 있으면 되는 것이다.

- Java Class로 BeanDefinition 생성
  - `AnnotationConfigApplicationContext`
- XML로 BeanDefinition 생성
  - `GenericXmlApplicationContext`
- ...

이렇게 다양한 ApplicationContext를 통해 어떤 형식이던지 설정 정보를 처리하여 최종적으로 `BeanDefinition`을 만들어 낼 수 있다.
