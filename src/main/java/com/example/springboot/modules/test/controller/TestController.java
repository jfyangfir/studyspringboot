package com.example.springboot.modules.test.controller;

import com.example.springboot.config.ResourceConfigBean;
import com.example.springboot.modules.test.entity.City;
import com.example.springboot.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// https SSL：自己生成一个CA证书命令：keytool -genkey -alias tomcat -keyalg RSA
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

    @Autowired
    private ResourceConfigBean resourceConfigBean;

    @RequestMapping("/download")
    @ResponseBody //返回一个对象而不是一个页面用此注解
    public ResponseEntity<Resource> download(@RequestParam String fileName) {

        try {
            String resourcePath = resourceConfigBean.getResourcePath() + fileName;
            Resource resource = new UrlResource(ResourceUtils.getURL(resourcePath));
            //CONTENT_DISPOSITION 设置下载文件的内容描述，可省略
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
                    .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment;filename=%s", fileName))
                    .body(resource);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    //    上传多个文件
    @PostMapping(value = "/files", consumes = "multipart/form-data")
    public String uploadFiles(MultipartFile[] files, RedirectAttributes redirectAttributes) {
        boolean isEmpty = true;
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }
            try {
                String destFilePath = "D:\\upload\\" + file.getOriginalFilename();
                File destFile = new File(destFilePath);
                file.transferTo(destFile);

                isEmpty = false;
            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("message", "Upload failed");
                return "redirect:/test/index";
            }
        }
        if (isEmpty) {
            redirectAttributes.addFlashAttribute("message", "Please select file");
        } else {
            redirectAttributes.addFlashAttribute("message", "Upload success.");
        }
        return "redirect:/test/index";
    }

    //    上传单个文件
    @PostMapping(value = "/file", consumes = "multipart/form-data")
    public String uploadFile(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {

//            addFlashAttribute()自动的将传递参数封装到后续的ModelMap
            redirectAttributes.addFlashAttribute("message", "Please select file");
            return "redirect:/test/index";
        }

        String resourcePath = resourceConfigBean.getResourcePath() + file.getOriginalFilename();
        try {
            File destFile = new File(ResourceUtils.getURL(resourcePath).getPath());
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Upload failed");
            return "redirect:/test/index";
        }
        redirectAttributes.addFlashAttribute("message", "Upload success.");
        return "redirect:/test/index";
    }

    /*
     * 127.0.0.1:8086/test/index
     * */
    @RequestMapping("/index")
    public String indexPage(ModelMap modelMap) {
        int countryId = 3;
        List<City> cities = cityService.getCitiesByCountryId(countryId);
//        modelMap.addAttribute("template","test/index");
        modelMap.addAttribute("city", cities.get(0));
        modelMap.addAttribute("cities", cities);
        modelMap.addAttribute("updateCityUrl", "/api/cityUpdate");
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

    /**
     * 1.项目启动时，ParameterFilter init.;
     * 2.ParameterFilter doFilter.==>Interceptor PreHandle()==>Around Controller==>
     * Before Controller==>After Controller==>Interceptor PostHandle()==>Interceptor AfterCompletion();
     * 3.filter 里的 destroy 方法在容器移除 servlet 时执行，同样只执行一次。这个方法会在所有的线程的 service() 方法执行完成或者超时后执行，
     * 调用这个方法后，容器不再把请求发送给这个servlet。这个方法给servlet释放占用的资源的机会，通常用来执行一些清理任务
     */

    /*
     * 127.0.0.1:8086/test/desc?key=fuck
     * */
    @RequestMapping("/desc")
    @ResponseBody
    public String testDesc(HttpServletRequest request, @RequestParam String key) {
        String keyTwo = request.getParameter("key");
        return "it is test module desc" + "---" + key + "======" + keyTwo;
    }
}
