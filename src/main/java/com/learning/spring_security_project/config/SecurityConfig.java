package com.learning.spring_security_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // active spring web security
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // tach backend va front - end
        httpSecurity.formLogin((foromLogin) -> foromLogin.loginProcessingUrl("/login"));
        httpSecurity.authorizeHttpRequests(
                request -> request.requestMatchers("/api/v1/auth/login", "/api/v1/auth/register")
                        .permitAll()
                        // .requestMatchers("/v1/admin/vip").hasAnyRole("ADMIN")
                        // .requestMatchers("/v1/admin/normal").hasAllRoles("USER", "ADMIn")
                        .anyRequest().authenticated()

        );
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withUsername("admin")
                .password("{noop}123")
                // .roles("admin", "user")
                .authorities("ROLE_USER", "ROLE_ADMIN")
                .build();

        UserDetails user = User.withUsername("user")
                .password("{noop}123")
                // .roles("user")
                .authorities("ROLE_USER")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
