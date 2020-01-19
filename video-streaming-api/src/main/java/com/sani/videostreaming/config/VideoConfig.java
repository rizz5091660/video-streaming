package com.sani.videostreaming.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import lombok.Getter;

@Configuration
@EnableWebFlux
@Getter
public class VideoConfig implements WebFluxConfigurer {
	@Value("${app.name}")
	private String appName;
	
	
}
