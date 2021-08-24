package com.met.it355pz.aspect;

import com.met.it355pz.payload.auth.AuthRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LoggingInterceptor {

    private Log log = LogFactory.getLog(this.getClass());

    @Before("within(com.met.it355pz.controller.AuthController)")
    public void interceptRequest(JoinPoint jp) {
        String method = jp.getSignature().getName();
        AuthRequest auth = (AuthRequest) jp.getArgs()[0];
        log.info(String.format("User tried to %s with credentials (username: %s, password: %s) ", method, auth.getUsername(), auth.getPassword()));

    }
}
