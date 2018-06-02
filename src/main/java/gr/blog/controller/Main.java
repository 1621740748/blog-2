package gr.blog.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("gr.blog.mapper")//将项目中对应的mapper类的路径加进来就可以了
@ComponentScan(basePackages = "gr.blog")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
