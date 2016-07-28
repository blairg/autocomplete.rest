package com.react.config;

import com.react.controller.converter.CityConverter;
import com.react.controller.converter.impl.CityConverterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Autowired
    @Bean(name="cityConverter")
    public CityConverter getCityConverter(){
        return new CityConverterImpl();
    }

}
