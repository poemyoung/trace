package com.trace.user;

import com.trace.dao.impl.UserConvertMapperImpl;
import com.trace.dao.impl.UserMapperImpl;
import com.trace.dao.repository.UserConvertMapper;
import com.trace.dao.repository.UserMapper;
import com.trace.service.user.UserIdConvertService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xzp
 * Created on 2021/2/7
 */
@Configuration
public class SpringConfig {
    @Bean
    public UserIdConvertService getUserIdConvert() {
        return new UserIdConvertService();
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
