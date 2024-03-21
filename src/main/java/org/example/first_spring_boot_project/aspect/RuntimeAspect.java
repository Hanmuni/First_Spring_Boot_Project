package org.example.first_spring_boot_project.aspect;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log
public class RuntimeAspect {
    @Before("within(org.example.first_spring_boot_project..*)")
    public void beforeMeasurement() {
        log.info("Measurement of runtime hasn't started yet!");
    }

    @Around("within(org.example.first_spring_boot_project..*)")
    public Object measureRuntime(ProceedingJoinPoint joinPoint) throws Throwable {
        Long startPoint = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        Long endPoint = System.currentTimeMillis();

        Long difference = endPoint - startPoint;

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        log.info("Method " + className + "." + methodName + " executed in " + difference + " ms");

        return proceed;
    }

    @After("within(org.example.first_spring_boot_project..*)")
    public void afterMeasurement() {
        log.info("Measurement of runtime has finished!");
    }
}
