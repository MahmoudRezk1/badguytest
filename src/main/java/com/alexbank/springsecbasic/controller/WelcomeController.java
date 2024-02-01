package com.alexbank.springsecbasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping(path = "/welcome")
    public String sayWelcome(){
        return "welcome to spring security";
    }
}
