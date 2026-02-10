package com.example.demo.infrastructure.aop;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class LoggingHandlerAspect {
  private static final String EXECUTING = "Executing [{}]-[{}]-[{}]";
  private static final String EXECUTED = "Executed [{}]-[{}] in {} ms";
  private static final String EXECUTED_WITH_ARGS = "Executed [{}]-[{}]-[{}] in {} ms";

  @Around(
      value =
          """
        execution(* *..*.application.usecase..* (..)) ||
        execution(* *..*.infrastructure.adapter.inbound..* (..))
    """)
  public Object logging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

    Trace trace = buildTrace(proceedingJoinPoint);

    log.info(EXECUTING, trace.getClassName(), trace.getMethod(), trace.getArgs());

    StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    Object result = proceedingJoinPoint.proceed();

    stopWatch.stop();
    long spendTimeMillis = stopWatch.getTotalTimeMillis();

    Optional.ofNullable(result)
        .ifPresentOrElse(
            r -> {
              if (result instanceof ResponseEntity<?>) {
                r = ((ResponseEntity<?>) result).getBody();
              }
              log.info(
                  EXECUTED_WITH_ARGS,
                  trace.getClassName(),
                  trace.getMethod(),
                  trace.getArgs(),
                  spendTimeMillis);
            },
            () -> log.info(EXECUTED, trace.getClassName(), trace.getMethod(), spendTimeMillis));
    return result;
  }

  private Trace buildTrace(ProceedingJoinPoint proceedingJoinPoint) {
    MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

    Trace trace =
        Trace.builder()
            .className(methodSignature.getDeclaringType().getSimpleName())
            .clazz(methodSignature.getDeclaringType().getName())
            .method(methodSignature.getName())
            .build();

    if (methodSignature.getParameterNames().length > 0
        && proceedingJoinPoint.getArgs().length > 0) {
      Map<String, Object> args =
          IntStream.range(0, methodSignature.getParameterNames().length)
              .boxed()
              .collect(
                  Collectors.toMap(
                      i -> methodSignature.getParameterNames()[i],
                      i ->
                          Objects.nonNull(proceedingJoinPoint.getArgs()[i])
                              ? proceedingJoinPoint.getArgs()[i]
                              : "",
                      (left, right) -> {
                        throw new AssertionError("Duplicates are not expected.");
                      },
                      LinkedHashMap::new));

      trace.setArgs(
          args.entrySet().stream()
              .map(entry -> entry.getKey() + "=" + entry.getValue())
              .collect(Collectors.joining(", ")));
    }

    return trace;
  }

  @Getter
  @Setter
  @Builder
  private static class Trace {
    private String className;
    private String clazz;
    private String method;
    private Object args;
  }
}
