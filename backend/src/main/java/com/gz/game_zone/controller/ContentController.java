package com.gz.game_zone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

    @GetMapping("/login")
    public String login(){
        return "Login";
    }

    @GetMapping("/req/signup")
    public String signup(){
        return "Signup";
    }
}
