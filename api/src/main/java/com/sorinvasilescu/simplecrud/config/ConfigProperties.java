package com.sorinvasilescu.simplecrud.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "simple-crud")
@Getter @Setter @NoArgsConstructor
public class ConfigProperties {
    private String jwtSecret;
    private Integer jwtExpiration;
}
