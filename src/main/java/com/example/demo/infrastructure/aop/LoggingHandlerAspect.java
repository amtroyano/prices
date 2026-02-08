package com.example.demo.infrastructure.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class LoggingHandlerAspect {
  private static final String EXECUTING = "Executing [{}]-[{}]";
  private static final String EXECUTED = "Executed [{}]-[{}] in {} ms";

  @Around(
      value =
          """
        execution(* *..*.application.usecase..* (..)) ||
        execution(* *..*.infrastructure.adapter.inbound..* (..))
    """)
  public Object logging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
    log.info(
        EXECUTING, methodSignature.getDeclaringType().getSimpleName(), methodSignature.getName());

    StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    Object result = proceedingJoinPoint.proceed();

    stopWatch.stop();
    log.info(
        EXECUTED,
        methodSignature.getDeclaringType().getSimpleName(),
        methodSignature.getName(),
        stopWatch.getTotalTimeMillis());

    return result;
  }
}
