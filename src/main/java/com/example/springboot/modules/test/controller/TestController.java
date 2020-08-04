package com.example.springboot.modules.test.controller;

import com.example.springboot.modules.test.entity.City;
import com.example.springboot.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Autowired
    private CityService cityService;


    /*
     * 127.0.0.1:8086/test/index
     * */
    @RequestMapping("/index")
    public String indexPage(ModelMap modelMap){
        int countryId=3;
        List<City> cities=cityService.getCitiesByCountryId(countryId);
//        modelMap.addAttribute("template","test/index");
        modelMap.addAttribute("city",cities.get(0));
        modelMap.addAttribute("cities",cities);
        modelMap.addAttribute("updateCityUrl","/api/cityUpdate");
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



/*
 * 127.0.0.1:8086/test/desc?key=fuck
 * */
    @RequestMapping("/desc")
    @ResponseBody
    public String testDesc(HttpServletRequest request, @RequestParam String key){
        String keyTwo=request.getParameter("key");
        return "it is test module desc"+"---"+key+"======"+keyTwo;
    }
}
