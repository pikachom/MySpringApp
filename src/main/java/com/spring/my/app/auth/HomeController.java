package com.spring.my.app.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class HomeController {
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {

        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null){
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/login-own")
    public String index() {
        return "login-own";
    }

    @ResponseBody
    @GetMapping("/authorizationTest")
    public String authorizationTest() {
        return "권한 확인";
    }

//    @GetMapping("/login-own")
//    public String login() {
//        return "login-own";
//    }
}
