package com.spring.my.app.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/error")
public class ErrorController {
    @GetMapping
    public String defaultError() {
        return "redirect:/";
    }

//    @GetMapping("/no-resource")
//    public String noResource() {
//        return "error/noResource";
//    }
//
//    @GetMapping("/server-error")
//    public String serverError() {
//        return "error/serverError";
//    }
}