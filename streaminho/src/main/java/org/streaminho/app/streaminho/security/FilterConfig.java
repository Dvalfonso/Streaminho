package org.streaminho.app.streaminho.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

public class FilterConfig {

    /**
     * Cuando se anota un filtro con @Component Spring Boot lo registra dos veces
     * Una por @Component y la otra cuando haces addFilterBefore
     * @param filter
     * @return
     */

    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> disableJwtFilterAutoRegistration(
            JwtAuthenticationFilter filter) {
        FilterRegistrationBean<JwtAuthenticationFilter> registration =
                new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }
}