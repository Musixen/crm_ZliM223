package ch.zli.m223.ksh18a.crm.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {

    @GetMapping("/login")
    public String login(){
        return login();
    }
}
