package com.yg.demo.user.utils;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName Mybatisconfig
 * @Description TODO
 * @Author YGuang
 * @Date 2019/7/2 10:30
 * @Version 1.0
 **/

@Configuration
public class Mybatisconfig {
    @Bean
    public ConfigurationCustomizer configurationCustomizer(){
        return configuration -> (configuration).setMapUnderscoreToCamelCase(true);

    }
}
