package com.example.springboot.modules.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @RequestMapping("/login")
    public String loginPage() {
        return "indexSimple";
    }

    @RequestMapping("/register")
    public String registerPage() {
        return "indexSimple";
    }

    @RequestMapping("/users")
    public String usersPage() {
        return "index";
    }
}
