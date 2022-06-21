package top.xeonwang.project01.annotation;

import lombok.extern.slf4j.Slf4j;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Chen Q.
 */
@Aspect
@Component
@Slf4j
public class ParamAspect {
    @Pointcut("@annotation(top.xeonwang.project01.annotation.AlgMethod)")
    public void entryPoint() {
    }

    @Before("entryPoint()")
    public void before() {
        log.info("before");
    }

//    @Around("entryPoint()")
//    public Object around(ProceedingJoinPoint joinPoint) {
//        log.info("around");
//        Object ret = null;
//        try {
//            ret = joinPoint.proceed(new Object[1]);
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        }
//
//        return ret;
//    }
}
