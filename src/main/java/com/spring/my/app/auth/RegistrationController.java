package com.spring.my.app.auth;

import com.spring.my.app.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@Slf4j
public class RegistrationController {
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

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