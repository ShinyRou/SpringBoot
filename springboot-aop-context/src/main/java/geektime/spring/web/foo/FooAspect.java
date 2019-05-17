package geektime.spring.web.foo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 前面类
 */
@Aspect
@Slf4j
@Component
public class FooAspect {

    /**
     * @Aspect、@Component形式 不需要@EnableAspectJAutoProxy
     */
    @Pointcut("execution(* geektime.spring.web.context.TestBean.hello())")
    public void printerBefore(){

    }
    @AfterReturning("printerBefore()")
    public void printAfter2() {
        log.info("after hello222()");
    }



    @AfterReturning("bean(parentBean*)")
    public void printAfter() {
        log.info("after hello()");
    }
}
