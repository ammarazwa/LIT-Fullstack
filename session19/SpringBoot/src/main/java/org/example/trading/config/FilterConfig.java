package org.example.trading.config;

import org.example.trading.filter.TradingAuthenticationFilter;
import org.example.trading.filter.TradingRateLimitFilter;
import org.example.trading.filter.TradingRequestLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<TradingAuthenticationFilter> authFilter(TradingAuthenticationFilter f) {
        FilterRegistrationBean<TradingAuthenticationFilter> reg = new FilterRegistrationBean<>(f);
        reg.setUrlPatterns(List.of("/api/*"));  // Hanya aktif di /api/**
        reg.setOrder(1);
        return reg;
    }


    @Bean
    public FilterRegistrationBean<TradingRateLimitFilter> rateLimitFilter(TradingRateLimitFilter f) {
        FilterRegistrationBean<TradingRateLimitFilter> reg = new FilterRegistrationBean<>(f);
        reg.setUrlPatterns(List.of("/api/*", "/api/v1/*"));
        reg.setOrder(2);
        return reg;
    }

    @Bean
    public FilterRegistrationBean<TradingRequestLoggingFilter> loggingFilter(TradingRequestLoggingFilter f) {
        FilterRegistrationBean<TradingRequestLoggingFilter> reg = new FilterRegistrationBean<>(f);
        reg.setUrlPatterns(List.of("/*")); // logging boleh global
        reg.setOrder(0);
        return reg;
    }
}
