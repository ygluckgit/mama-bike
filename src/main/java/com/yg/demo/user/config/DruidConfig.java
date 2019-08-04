package com.yg.demo.user.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.sun.javafx.collections.MappingChange;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName DruidConfig
 * @Description TODO
 * @Author YGuang
 * @Date 2019/6/28 15:36
 * @Version 1.0
 **/
//表示这个是个配置类 可以和其他啊配置文件一样给容器添加组件
@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
   public DataSource druid(){
    return new DruidDataSource();
    }
    //配置druid监控
    //1.配置一个管理后太的servlet stat:统计
    @Bean
    public ServletRegistrationBean registrationBean(){
        ServletRegistrationBean bean=new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String,String>map=new HashMap <>();
        map.put("loginUsername","root");
        map.put("loginPassword","123456");
        map.put("allow","");
        bean.setInitParameters(map);
        return bean;
    }
    //2.配置一个filter
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean bean=new FilterRegistrationBean(new WebStatFilter());
        //bean.setFilter(new WebStatFilter());
        Map<String,String>fm=new HashMap <>();
        fm.put("excluion","*.js,*.css,/druid/*");
        bean.setInitParameters(fm);
        bean.setUrlPatterns(Arrays.asList("*/"));
        return bean;

    }


}
