package com.yzn.springboot.crudapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails yazan = User.builder()
                .username("yazan")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails aws = User.builder()
                .username("aws")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER")
                .build();

        UserDetails rashed = User.builder()
                .username("rashed")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();

        return new InMemoryUserDetailsManager(yazan,aws,rashed);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(configuer ->
            configuer
                    .requestMatchers(HttpMethod.GET,"/api/employees").hasRole("EMPLOYEE")
                    .requestMatchers(HttpMethod.GET,"/api/employees/**").hasRole("EMPLOYEE")
                    .requestMatchers(HttpMethod.POST,"/api/employees").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.PUT,"/api/employees").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.PATCH,"/api/employees/**").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.DELETE,"/api/employees/**").hasRole("ADMIN")
        );

        // Use HTTP Basic Authentication
        httpSecurity.httpBasic(Customizer.withDefaults());

        // Disable Cross Site Request Forgery (CSRF)
        // In general, not required for stateless REST APIs that use POST, PUT, DELETE, and/or PATCH
        httpSecurity.csrf(csrf -> csrf.disable());

        return httpSecurity.build();
    }

}
