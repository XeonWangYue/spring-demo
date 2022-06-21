package top.xeonwang.project01.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import top.xeonwang.project01.model.AlgRunable;
import top.xeonwang.project01.annotation.Algorithm;

import javax.annotation.Resource;
import java.io.File;
import java.util.Map;

/**
 * @author Chen Q.
 */
@Component
@Slf4j
public class AlgContextListener implements ApplicationListener<ContextRefreshedEvent> {
    @Resource(name = "Register")
    Map algs;

    @Value("${filesystem.path}")
    String filepath;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            Map<String, Object> beans = event.getApplicationContext().getBeansWithAnnotation(Algorithm.class);
            for (Object bean : beans.values()) {
                log.info(">>>>" + (bean == null ? "null" : ((AlgRunable) bean).commandID));
                algs.put(((AlgRunable) bean).commandID, bean);
            }
            log.info("=====ContextRefreshedEvent=====" + "find alg: "+algs.size());
            boolean mkdirs = false;
            File temp = new File(filepath);
            if (!temp.exists()) {
                mkdirs = temp.mkdirs();
            }
            log.info("=====ContextRefreshedEvent=====" + "create folder: " + mkdirs);

        }
    }
}
