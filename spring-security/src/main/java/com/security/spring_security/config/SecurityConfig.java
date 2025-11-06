package com.security.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        //http.authorizeHttpRequests((request)->request.anyRequest().denyAll());
        //http.authorizeHttpRequests((request)->request.anyRequest().permitAll());
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                .requestMatchers("/notices", "/contact", "/error").permitAll());
        //http.formLogin(withDefaults());
        http.formLogin(flc->flc.disable());
        //http.httpBasic(withDefaults());
        http.httpBasic(htp->htp.disable());
        return http.build();
    }
}
