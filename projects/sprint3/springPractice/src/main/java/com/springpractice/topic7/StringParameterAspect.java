package com.springpractice.topic7;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class StringParameterAspect {

    @Before("execution(* com.springpractice.topic7.UserController.*(..)) && args(stringParam,..)")
    public void logStringParameter(JoinPoint joinPoint, String stringParam) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Method '" + methodName + "' called with String parameter: " + stringParam);
    }
}
