package com.sample;

import com.sample.controller.TaskerCrudController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackageClasses = {TaskerCrudController.class})
public class SampleCrudDockerApp {

    public static void main(String[] args) {
        SpringApplication.run(SampleCrudDockerApp.class, args);
        }

}
