package geektime.spring.web.context;

import geektime.spring.web.foo.FooConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
@Slf4j
public class AopContextApp implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(AopContextApp.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		//获取父上下文  增强定义在父上下文之中
		ApplicationContext fooContext = new AnnotationConfigApplicationContext(FooConfig.class);
		//通过代码指定子上下文
		ClassPathXmlApplicationContext barContext = new ClassPathXmlApplicationContext(
				new String[] {"applicationContext.xml"}, fooContext);
		//从父上下文 获取Bean
		TestBean bean = fooContext.getBean("parentBeanX", TestBean.class);
		bean.hello();

		log.info("=============");

		//获取定义在子上下文中的Bean
		bean = barContext.getBean("parentBeanX", TestBean.class);
		bean.hello();

		//从子上下文中获取，因为不存在所以会从父上下文中获取
		bean = barContext.getBean("parentBeanY", TestBean.class);
		bean.hello();
	}
}
