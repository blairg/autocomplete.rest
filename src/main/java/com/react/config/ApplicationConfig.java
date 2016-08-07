package com.react.config;

import com.react.controller.converter.CityConverter;
import com.react.controller.converter.impl.CityConverterImpl;
import com.react.service.CacheService;
import com.react.service.impl.CacheServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;

@Configuration
//@EnableCaching
public class ApplicationConfig {

    @Bean
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheCacheManager().getObject());
    }

    @Bean
    public EhCacheManagerFactoryBean ehCacheCacheManager() {
        EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
        cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
        cmfb.setShared(true);

        return cmfb;
    }

    @Autowired
    @Bean(name="cityConverter")
    public CityConverter getCityConverter(){
        return new CityConverterImpl();
    }

    @Autowired
    @Bean(name="cacheService")
    @Scope("singleton")
    public CacheService getCacheService()
    {
        return new CacheServiceImpl();
    }
}
