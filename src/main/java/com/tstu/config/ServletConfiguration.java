package com.tstu.config;

import com.tstu.servlets.FindFilmServlet;
import com.tstu.servlets.HomeServlet;
import com.tstu.servlets.LoginServlet;
import com.tstu.servlets.RegisterServlet;
import com.tstu.servlets.filters.SecurityServletFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
public class ServletConfiguration {

//    @Bean
//    public ViewResolver internalResourceViewResolver() {
//        InternalResourceViewResolver bean = new InternalResourceViewResolver();
//        bean.setViewClass(JstlView.class);
//        bean.setPrefix("/WEB-INF/jsp/");
//        bean.setSuffix(".jsp");
//        return bean;
//    }

    @Bean
    public ServletRegistrationBean homeServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new HomeServlet(), "/index");
        bean.setLoadOnStartup(1);
        return bean;
    }

    @Bean
    public ServletRegistrationBean loginServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new LoginServlet(), "/user/login");
        bean.setLoadOnStartup(1);
        return bean;
    }

    @Bean
    public ServletRegistrationBean registerServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new RegisterServlet(), "/user/register");
        bean.setLoadOnStartup(1);
        return bean;
    }

    @Bean
    public ServletRegistrationBean findFilmServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new FindFilmServlet(), "/films");
        bean.setLoadOnStartup(1);
        return bean;
    }

    @Bean
    public FilterRegistrationBean securityServletFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new SecurityServletFilter());
        bean.addUrlPatterns("/secured/*");
        return bean;
    }
}
