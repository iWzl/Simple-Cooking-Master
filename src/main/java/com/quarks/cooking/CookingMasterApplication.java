package com.quarks.cooking;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author  Leo Wang
 */
@SpringBootApplication
@MapperScan("com.quarks.cooking.mapper")
public class CookingMasterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CookingMasterApplication.class, args);
    }

}
