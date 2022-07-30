package com.lucodes.myfancypdfinvoices.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
@Slf4j
public class MyfancypdfinvoicesSpringbootApplication {

    @Bean
    public InitializingBean runner(DataSource dataSource) {
        return () -> {
            log.info("This is the datasource being used: " + dataSource);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(MyfancypdfinvoicesSpringbootApplication.class, args);
    }

}
