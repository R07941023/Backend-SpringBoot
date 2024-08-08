package com.dev.template.exception;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

import com.dev.template.dto.RestResult;

@Aspect
@Component
public class TimeoutAspect {

    private final ExecutorService executor = Executors.newCachedThreadPool();

    @Around("@annotation(timeout)")
    public Object around(ProceedingJoinPoint joinPoint, Timeout timeout) throws Throwable {
        Future<Object> future = executor.submit(() -> {
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        });

        RestResult restResult = new RestResult();
        String errorMessage;
        try {
            return future.get(timeout.value(), TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            future.cancel(true);
            errorMessage = "task timeout";
            restResult.setRetCode(-1);
            restResult.setRetDetail(errorMessage);
        } catch (InterruptedException | ExecutionException e) {
            errorMessage = "task fail " + e.getMessage();
            e.printStackTrace();
            restResult.setRetCode(-1);
            restResult.setRetDetail(errorMessage);
        } finally {
            executor.shutdown();
        }
        return restResult;
    }
}