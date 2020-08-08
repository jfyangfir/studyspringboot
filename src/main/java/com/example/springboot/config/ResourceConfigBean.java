package com.example.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config/application.properties")
public class ResourceConfigBean {

    @Value("${spring.resource.path}")
    private String resourcePath;
    @Value("${spring.resource.folder.windows}")
    private String localPathForWindows;
    @Value("${spring.resource.folder.linux}")
    private String localPathForLinux;

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getLocalPathForWindows() {
        return localPathForWindows;
    }

    public void setLocalPathForWindows(String localPathForWindows) {
        this.localPathForWindows = localPathForWindows;
    }

    public String getLocalPathForLinux() {
        return localPathForLinux;
    }

    public void setLocalPathForLinux(String localPathForLinux) {
        this.localPathForLinux = localPathForLinux;
    }

    @Override
    public String toString() {
        return "ResourceConfigBean{" +
                "resourcePath='" + resourcePath + '\'' +
                ", localPathForWindows='" + localPathForWindows + '\'' +
                ", localPathForLinux='" + localPathForLinux + '\'' +
                '}';
    }
}
