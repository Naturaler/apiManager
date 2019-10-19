package com.yrx.datasourcemanager.manager.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by r.x on 2019/10/19.
 */
@Aspect
@Slf4j
@Component
public class DemoAspect {

    @Pointcut("execution(public * com.yrx.datasourcemanager.manager.api..*.*(..))")
    private void pointcut() {

    }

    @Before("pointcut()")
    private void beforeExeApi(JoinPoint joinPoint) {
        log.info("Before");
    }

    @After("pointcut()")
    private void afterExeApi(JoinPoint joinPoint) {
        log.info("After");
    }

    @AfterThrowing("pointcut()")
    private void afterThrowingExeApi(JoinPoint joinPoint) {
        log.info("AfterThrowing");
    }

    @AfterReturning("pointcut()")
    private void afterReturningExeApi(JoinPoint joinPoint) {
        log.info("AfterReturning");
    }

    @Around("pointcut()")
    private Object aroundExeApi(ProceedingJoinPoint joinPoint) {
        log.info("Around");
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
}
