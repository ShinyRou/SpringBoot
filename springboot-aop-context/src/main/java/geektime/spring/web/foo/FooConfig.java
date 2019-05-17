package geektime.spring.web.foo;

import geektime.spring.web.context.TestBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class FooConfig {
    @Bean
    public TestBean parentBeanX() {
        return new TestBean("parent");
    }

    @Bean
    public TestBean parentBeanY() {
        return new TestBean("parent");
    }

    @Bean
    public FooAspect fooAspect() {
        return new FooAspect();
    }
}
