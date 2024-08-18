package com.example.hober.global;

import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedHeaders(Collections.singletonList("*"));
            config.setAllowedMethods(Collections.singletonList("*"));
            config.addAllowedOriginPattern("*"); // 모든 Origin 허용
            config.setAllowCredentials(true);
            return config;
        };
    }

    @Bean
    public SecurityFilterChain filterChain(
        HttpSecurity builder
    ) throws Exception {

        AntPathRequestMatcher[] apiWhitelist = new AntPathRequestMatcher[]{
            new AntPathRequestMatcher("/swagger-ui/**"),
            new AntPathRequestMatcher("/swagger-resources/**"),
            new AntPathRequestMatcher("/**/api-docs/**"),
            new AntPathRequestMatcher("/"),
            new AntPathRequestMatcher("/users/signup"),
            new AntPathRequestMatcher("/users/login"),
            new AntPathRequestMatcher("/users/send-mail"),
            new AntPathRequestMatcher("/users/confirm-mail"),
        };

        builder.authorizeHttpRequests(authorizeHttpRequests ->
            authorizeHttpRequests.requestMatchers("/**").permitAll()
                .anyRequest().authenticated()
        );
        builder.csrf(AbstractHttpConfigurer::disable);          // jwt Token 사용을 위한 csrf 비활성화
        builder.formLogin(AbstractHttpConfigurer::disable);     //
        builder.sessionManagement(sessionManagement ->
            sessionManagement.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS));      // token 기반 인증 방식으로 STATELESS 설정
        builder.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(
            corsConfigurationSource()));
        builder.anonymous(AbstractHttpConfigurer::disable);
//        builder.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return builder.build();
    }
}
