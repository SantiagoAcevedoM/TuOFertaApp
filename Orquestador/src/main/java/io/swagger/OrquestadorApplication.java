package io.swagger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication

//@ComponentScan(basePackages = { "io.swagger", "io.swagger.api" })
public class OrquestadorApplication {


    public static void main(String[] args) throws Exception {
        new SpringApplication(OrquestadorApplication.class).run(args);
    }

}
