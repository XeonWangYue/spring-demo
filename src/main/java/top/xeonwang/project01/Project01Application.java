package top.xeonwang.project01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication@MapperScan("top.xeonwang.project01.mapper")
public class Project01Application {
    public static void main(String[] args) {
        SpringApplication.run(Project01Application.class, args);
    }
}