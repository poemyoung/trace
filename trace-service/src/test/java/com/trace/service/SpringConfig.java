package com.trace.service;

import com.trace.dao.impl.UserConvertMapperImpl;
import com.trace.dao.repository.UserConvertMapper;
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
}
