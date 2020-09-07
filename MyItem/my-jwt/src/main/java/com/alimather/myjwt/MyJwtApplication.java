package com.alimather.myjwt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.alimather.myjwt.demo.dao")
@SpringBootApplication
public class MyJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyJwtApplication.class, args);
    }

}
