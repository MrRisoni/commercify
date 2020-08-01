package core;

import models.HibernateUtil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAutoConfiguration
@ComponentScan
@EnableScheduling
@SpringBootApplication
@EntityScan("models")
@EnableJpaRepositories("spring_repos")
public class Application {

     public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        HibernateUtil.buildSessionFactory();

    }
}
