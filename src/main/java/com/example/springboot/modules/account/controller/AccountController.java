package com.example.springboot.modules.account.controller;

import com.example.springboot.modules.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String loginPage() {
        return "indexSimple";
    }

    @RequestMapping("/logout")
    public String logOut(ModelMap modelMap) {
        userService.logout();
        modelMap.addAttribute("template","account/login");
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
