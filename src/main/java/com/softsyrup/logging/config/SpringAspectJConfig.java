package com.softsyrup.logging.config;

import com.softsyrup.logging.aspect.LoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class SpringAspectJConfig {
    @Bean
    public LoggingInterceptor loggingInterceptor(){
        return new LoggingInterceptor();
    }
}
