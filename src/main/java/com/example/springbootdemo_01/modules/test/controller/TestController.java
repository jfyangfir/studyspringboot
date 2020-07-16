package com.example.springbootdemo_01.modules.test.controller;

import com.example.springbootdemo_01.modules.test.vo.applicationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Value("${server.port}")
    private int port;
    @Value("${com.thornBird.name}")
    private String name;
    @Value("${com.thornBird.age}")
    private int age;
    @Value("${com.thornBird.desc}")
    private String desc;
    @Value("{com.thornBird.random}")
    private String random;

    @Autowired
    private applicationTest applicationTest;

    @RequestMapping("/test/config")
    @ResponseBody
    public String configInfo(){
        StringBuffer sb=new StringBuffer();
         sb.append(port).append("---")
           .append(name).append("---")
           .append(age).append("---")
           .append(desc).append("---")
           .append(random).append("---").append("<br>");

        sb.append(applicationTest.getName1()).append("---")
           .append(applicationTest.getAge1()).append("---")
           .append(applicationTest.getDesc1()).append("---")
           .append(applicationTest.getRandom1()).append("---").append("<br>");

        return sb.toString();
    }

    @RequestMapping("/test/desc")
    @ResponseBody
    public String testDesc(){
        return "it is test module desc";
    }
}
