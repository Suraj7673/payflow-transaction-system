package com.payflow.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello(Authentication auth) {
        return "Hello " + auth.getName();
    }
}