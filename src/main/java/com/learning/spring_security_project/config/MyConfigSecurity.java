package com.learning.spring_security_project.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class MyConfigSecurity {
    private final UserDetailsService userDetailsService;
//
//    // user
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("user")
//                .password("{noop}user")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.withUsername("admin")
//                .password("{noop}admin")
//                .roles("ADMIN")
//                .build();
//
//        UserDetails mod = User.withUsername("mod")
//                .password("{noop}mod")
//                .roles("MOD")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin, mod);
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/public/**").permitAll() // api -> public/a /b
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                .logout(Customizer.withDefaults());
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().authenticated())
//                .formLogin(form -> form.loginPage("/login").permitAll()
//                        .defaultSuccessUrl("/private")
//                        .failureUrl("/login?error=true"))
//                .logout(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable);

//                http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/mod/**").hasAnyRole("MOD", "ADMIN")
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                        .logout(Customizer.withDefaults());


                    http
                            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                                    .requestMatchers("/admin/**").hasRole("ADMIN")
                                    .anyRequest().authenticated())
                            .userDetailsService(userDetailsService)
                            .formLogin(form -> form
                                    .defaultSuccessUrl("/user/info", true))
                            .logout(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
