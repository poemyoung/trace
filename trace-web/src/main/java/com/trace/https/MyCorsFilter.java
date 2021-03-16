package com.trace.https;

import com.thetransactioncompany.cors.CORSConfiguration;
import com.thetransactioncompany.cors.CORSConfigurationException;
import com.thetransactioncompany.cors.CORSFilter;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author xzp
 * Created on 2021/3/16
 */
@Component
public class MyCorsFilter extends CORSFilter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Properties props = new Properties();
        CORSConfiguration config = null;
        try {
            InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("cors.properties");
            assert resourceAsStream != null;
            props.load(resourceAsStream);
            config = new CORSConfiguration(props);
        } catch (CORSConfigurationException | IOException e) {
            e.printStackTrace();
        }
        assert config != null;
        this.setConfiguration(config);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 开启cors过滤 ,默认开启
        if (this.getConfiguration().allowGenericHttpRequests) {
            super.doFilter(request, response, chain);
        }else{
            // 未开启cors过滤
            chain.doFilter(request, response);
        }
    }
}
