package com.bigfanoftim.springcorebasic.findbean;

import com.bigfanoftim.springcorebasic.AppConfig;
import com.bigfanoftim.springcorebasic.user.service.UserService;
import com.bigfanoftim.springcorebasic.user.service.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    
    @Test
    @DisplayName("모든 Bean 출력")
    public void findAllBean() throws Exception {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("bean = " + bean);
        }
    }

    @Test
    @DisplayName("모든 Application Bean 출력")
    public void findApplicationBeans() throws Exception {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("bean = " + bean);
            }
        }
    }

    @Test
    @DisplayName("모든 Spring Infrastructure Bean 출력")
    public void findInfrastructureBeans() throws Exception {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            /**
             * ROLE_INFRASTRUCTURE : Spring이 내부에서 사용하는 Bean
             */
            if (beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("bean = " + bean);
            }
        }
    }

    @Test
    @DisplayName("이름으로 Bean 조회")
    public void findBeanByName() throws Exception {
        UserService userService = ac.getBean("userService", UserService.class);

        assertThat(userService).isInstanceOf(UserServiceImpl.class);
    }

    /**
     * 구체 타입(UserServiceImpl)으로 조회가 가능
     */
    @Test
    @DisplayName("타입으로 Bean 조회")
    public void findBeanByType() throws Exception {
        UserService userService = ac.getBean(UserService.class);

        assertThat(userService).isInstanceOf(UserServiceImpl.class);
    }

    @Test
    @DisplayName("이름으로 Bean 조회 실패")
    public void findBeanByNameX() throws Exception {
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxx"));
    }
}
