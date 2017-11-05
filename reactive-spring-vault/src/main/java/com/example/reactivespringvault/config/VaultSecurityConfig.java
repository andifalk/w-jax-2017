package com.example.reactivespringvault.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class VaultSecurityConfig {

    @Value("${userpass}")
    private String userPassword;

    @Value("${adminpass}")
    private String adminPassword;

    @Bean
    public MapReactiveUserDetailsService userDetailsRepository() {
        UserDetails standardUser = User.withDefaultPasswordEncoder()
                .username("user")
                .password(userPassword)
                .roles("USER")
                .build();
        UserDetails adminUser = User.withDefaultPasswordEncoder()
                .username("admin")
                .password(adminPassword)
                .roles("USER", "ADMIN")
                .build();
        return new MapReactiveUserDetailsService(standardUser, adminUser);
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                .anyExchange().authenticated()
                .and()
                .httpBasic();
        return http.build();
    }

}
