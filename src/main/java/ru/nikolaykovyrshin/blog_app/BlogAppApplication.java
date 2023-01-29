package ru.nikolaykovyrshin.blog_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class BlogAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogAppApplication.class, args);
    }
}
