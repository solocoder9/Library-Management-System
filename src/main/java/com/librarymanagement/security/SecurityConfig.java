package com.librarymanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers("/auth/**", "/ui/**", "/static/**", "/css/**", "/js/**", "/images/**", "/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/books/**").permitAll()
                
                // Book management (Admin/Librarian only)
                .requestMatchers(HttpMethod.POST, "/books/**").hasAnyRole("ADMIN", "LIBRARIAN")
                .requestMatchers(HttpMethod.PUT, "/books/**").hasAnyRole("ADMIN", "LIBRARIAN")
                .requestMatchers(HttpMethod.DELETE, "/books/**").hasAnyRole("ADMIN", "LIBRARIAN")
                
                // Borrowing (Members only)
                .requestMatchers("/borrow/**", "/return/**").hasRole("MEMBER")
                
                // All other requests require authentication
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}