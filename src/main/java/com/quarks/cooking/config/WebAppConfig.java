package com.quarks.cooking.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Leonardo iWzl
 * @version 1.0
 */

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Value("${leonardo.filepath.image:./image}")
    private String imageFilePath;

    @Value("${leonardo.filepath.video:./video}")
    private String videoFilePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:"+imageFilePath);
        registry.addResourceHandler("/video/**")
                .addResourceLocations("file:"+videoFilePath);
    }
}
