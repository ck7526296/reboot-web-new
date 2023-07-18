package com.reboot.rebootweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan(basePackages = "com.reboot.rebootweb.mapper")
public class RebootWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(RebootWebApplication.class, args);
    }

}
