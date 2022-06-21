package top.xeonwang.project01.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
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
public class AlgAspect {
    @Pointcut("@annotation(top.xeonwang.project01.annotation.Algorithm)")
    public void entry() {
    }

    @Before(value = "entry()")
    public void before() {
        //输出连接点的信息
        log.info("Algorithm");
    }

//    TODO @Around()

}
