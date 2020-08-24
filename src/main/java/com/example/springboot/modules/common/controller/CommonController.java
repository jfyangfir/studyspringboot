package com.example.springboot.modules.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class CommonController {

        @RequestMapping("/dashboard")
        public String dashboardPage(){
              return "index";
}

}
