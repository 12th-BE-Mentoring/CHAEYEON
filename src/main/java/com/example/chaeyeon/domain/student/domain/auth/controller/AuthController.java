package com.example.chaeyeon.domain.student.domain.auth.controller;

import com.example.chaeyeon.domain.student.domain.auth.dto.LoginRequest;
import com.example.chaeyeon.domain.student.domain.auth.dto.SignUpRequest;
import com.example.chaeyeon.domain.student.domain.auth.service.LoginService;
import com.example.chaeyeon.domain.student.domain.auth.service.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final LoginService loginService;
    private final SignupService signupService;

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest request){
        loginService.login(request);
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody SignUpRequest request){
        signupService.signUp(request);
    }
}
