package com.geekbrains.book.store.monitor;

import com.geekbrains.book.store.beans.ServiceCall;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceCallsMonitor {

    private final ServiceCall serviceCall;

    public ServiceCallsMonitor(ServiceCall serviceCall) {
        this.serviceCall = serviceCall;
    }

    @Before("execution(public * com.geekbrains.book.store.services.*.*(..))")
    public void beforeServiceMethodCalls(JoinPoint joinPoint){
        serviceCall.addMethodCall(joinPoint.getSignature().toString());
    }

}
