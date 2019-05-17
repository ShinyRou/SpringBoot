package com.zhujun.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * className: AopDemo
 * create by: zhujun
 * description: AopDemo
 * create time: 2019/5/17 14:58
 */
@Aspect
@Component
public class AopDemo {

    private  final Logger log = LoggerFactory.getLogger(this.getClass());

    private long startTime;
    private long endTime;

    /**
     * 格式：
     *
     * execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern)throws-pattern?)
     * 括号中各个pattern分别表示：
     *
     * 修饰符匹配（modifier-pattern?）
     * 返回值匹配（ret-type-pattern）可以为*表示任何返回值,全路径的类名等
     * 类路径匹配（declaring-type-pattern?）
     * 方法名匹配（name-pattern）可以指定方法名 或者 *代表所有, set* 代表以set开头的所有方法
     * 参数匹配（(param-pattern)）可以指定具体的参数类型，多个参数间用“,”隔开，各个参数也可以用“*”来表示匹配任意类型的参数，如(String)表示匹配一个String参数的方法；(*,String) 表示匹配有两个参数的方法，第一个参数可以是任意类型，而第二个参数是String类型；可以用(..)表示零个或多个任意参数
     * 异常类型匹配（throws-pattern?）
     * 其中后面跟着“?”的是可选项
     *
     *
     * UserService接口的任意方法：
     * execution(* com.coffee.service.UserService.*(..))
     *
     * 定义在com.coffee.service包里的任意方法的执行：
     * execution(* com.coffee.service.*.*(..))
     * #第一个 .* 代表任意类, 第二个 .* 代表人以方法
     */
    //定义切入点
    //Pointcut表达式
    @Pointcut("execution(* *com.zhujun.controller.HelloController.sayHello(*) )")
    //Pointcut签名
    private void controllerPointCut(){ }


    /**
     * JoinPoint对象封装了SpringAop中切面方法的信息,在切面方法中添加JoinPoint参数,就可以获取到封装了该方法信息的JoinPoint对象.
     *
     * 　　　　常用api:
     *
     * 方法名	功能
     * Signature getSignature();	获取封装了署名信息的对象,在该对象中可以获取到目标方法名,所属类的Class等信息
     * Object[] getArgs();	获取传入目标方法的参数对象
     * Object getTarget();	获取被代理的对象
     * Object getThis();	获取代理对象
     */
    @Before("controllerPointCut()")
    public void beforeRequest(JoinPoint joinPoint){
        log.info("前置切面before……");
        startTime = System.currentTimeMillis();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String requestURI = request.getRequestURI();
        String remoteAddr = request.getRemoteAddr();   //这个方法取客户端ip"不够好"
        String requestMethod = request.getMethod();
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("请求url=" + requestURI + ",客户端ip=" + remoteAddr + ",请求方式=" + requestMethod + ",请求的类名=" + declaringTypeName + ",方法名=" + methodName + ",入参=" + args);
    }


    /*@After注解表示在方法执行之后执行*/
    @After("controllerPointCut()")
    public void after() {
        endTime = System.currentTimeMillis();
        log.info("后置切面after……");
    }

    /*@AfterReturning注解用于获取方法的返回值*/
    @AfterReturning(pointcut = "controllerPointCut()", returning = "object")
    public void getAfterReturn(Object object) {
        log.info("本次接口耗时={}ms", endTime-startTime);
        log.info("afterReturning={}", object.toString());
    }
}
