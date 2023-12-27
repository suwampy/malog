package com.malog.member.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckApi {
    @GetMapping("/health")
    public String healthCheck() {
        return "hello world";
    }
}
