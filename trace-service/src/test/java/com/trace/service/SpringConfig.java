package com.trace.service;

import com.trace.dao.impl.UserConvertMapperImpl;
import com.trace.dao.impl.UserMapperImpl;
import com.trace.dao.repository.UserConvertMapper;
import com.trace.dao.repository.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xzp
 * Created on 2021/2/7
 */
@Configuration
public class SpringConfig {
    @Bean
    public UserIdConvert getUserIdConvert() {
        return new UserIdConvert();
    }
    @Bean
    public UserConvertMapper getUserConvertMapper() {
        return new UserConvertMapperImpl();
    }
    @Bean
    public UserMapper getUserMapper() {
        return new UserMapperImpl();
    }
}
