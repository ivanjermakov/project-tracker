package com.gmail.ivanjermakov1.projecttracker.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileConfig implements WebMvcConfigurer {
	
	@Value("${fileupload.path}")
	private String fileuploadPath;
	
	@Value("${web.static.resources.path}")
	private String resourcesPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
				.addResourceHandler("/" + resourcesPath + "**")
				.addResourceLocations("file:/" + fileuploadPath);
	}
	
}
