package com.spring.my.app.auth.controller;

import com.spring.my.app.auth.notdecided.RegistrationForm;
import com.spring.my.app.auth.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/register")
@Slf4j
public class RegistrationController {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    //@RequiredArgsConstructor 를 맨 위에 넣으면 요런 생성자가 필요 없어짐
    public RegistrationController(
            UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        return "register";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        log.info("email : {}", form.getEmail());
        log.info("password : {}", form.getPassword());
        log.info("name : {}", form.getName());
        userRepo.save(form.toUser(passwordEncoder));
        return "redirect:/";
    }
}