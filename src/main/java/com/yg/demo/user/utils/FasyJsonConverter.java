package com.yg.demo.user.utils;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;

/**
 * @ClassName FasyJsonConverter
 * @Description TODO 使用于将springboot默认的jackson转换为fastjason
 * @Author YGuang
 * @Date 2019/7/5 23:27
 * @Version 1.0
 **/

@Component("消息格式转换器")
public class FasyJsonConverter {
    @Bean
    public HttpMessageConverters fastJsonMessageConverters(){
        //定义conver转换消息对象
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter=new FastJsonHttpMessageConverter();
        //添加配置信息
        FastJsonConfig fastJsonConfig=new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter=fastJsonHttpMessageConverter;
        return new HttpMessageConverters(converter);
    }
}
