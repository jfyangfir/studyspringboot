package com.example.springboot.modules.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//https SSL：自己生成一个CA证书命令：keytool -genkey -alias tomcat -keyalg RSA
@Controller
@RequestMapping("/test")
public class TestController {

//    @Value("${server.port}")
//    private int port;
//    @Value("${com.thornBird.name}")
//    private String name;
//    @Value("${com.thornBird.age}")
//    private int age;
//    @Value("${com.thornBird.desc}")
//    private String desc;
//    @Value("{com.thornBird.random}")
//    private String random;

//    @Autowired
//    private com.example.springboot.modules.test.vo.applicationTest applicationTest;

    @RequestMapping("/index")
    public String indexPage(){
        return "index";
    }

//    @RequestMapping("/test/config")
//    @ResponseBody
//    public String configInfo(){
//        StringBuffer sb=new StringBuffer();
//         sb.append(port).append("---")
//           .append(name).append("---")
//           .append(age).append("---")
//           .append(desc).append("---")
//           .append(random).append("---").append("<br>");
//
//        sb.append(applicationTest.getName1()).append("---")
//           .append(applicationTest.getAge1()).append("---")
//           .append(applicationTest.getDesc1()).append("---")
//           .append(applicationTest.getRandom1()).append("---").append("<br>");
//
//        return sb.toString();
//    }

    @RequestMapping("/test/desc")
    @ResponseBody
    public String testDesc(){
        return "it is test module desc";
    }
}
