package com.adityakr1403.elibrary.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class LibrarySecurityConfig {

    private static final String[] SECURED_PATHS = {
            "/api/books/**"
    };

    private static final String[] UNSECURED_PATHS = {
            "/api/books/all",
            "/api/books/{id}",
            "/api/user/**"
    };
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new LibraryUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(UNSECURED_PATHS).permitAll()
                .requestMatchers(SECURED_PATHS).hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().httpBasic().and().build();

    }
}
