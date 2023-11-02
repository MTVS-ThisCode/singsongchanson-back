package com.singsongchanson;

import com.singsongchanson.global.configuration.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class SingsongchansonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SingsongchansonApplication.class, args);
    }

}
