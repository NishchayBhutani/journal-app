package com.nishchay.journalApp.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping("/health-check")
    public String healthCheck(HttpServletRequest request) {
        return "OK : " + request.getSession().getId();
    }
}
