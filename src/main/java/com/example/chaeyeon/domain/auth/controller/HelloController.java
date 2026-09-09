package com.example.chaeyeon.domain.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    public String hello(){
        return "Hello";
    }
}
