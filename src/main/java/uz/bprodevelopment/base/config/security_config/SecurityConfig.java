package uz.bprodevelopment.base.config.security_config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import uz.bprodevelopment.base.config.jwt_config.JwtAuthenticationFilter;

import java.util.Arrays;
import java.util.Collections;

import static uz.bprodevelopment.app.util.ApiUrls.*;
import static uz.bprodevelopment.base.constant.BaseApiUrls.*;
import static uz.bprodevelopment.base.constant.Constants.*;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                AUTH_URL + "/**",
                                REGISTER_URL + "/confirm/**",
                                "/v2/api-docs",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-ui.html"
                        )
                        .permitAll()

                        .requestMatchers(USER_URL, USER_URL + "/**", ROLE_URL).hasAnyAuthority(ROLE_ADMIN)

                        .requestMatchers(HttpMethod.POST, PRODUCT_URL, CATEGORY_URL).hasAnyAuthority(ROLE_WAITER, ROLE_ADMIN)
                        .requestMatchers(HttpMethod.PUT, PRODUCT_URL, CATEGORY_URL, PRODUCT_URL + "/**").hasAnyAuthority(ROLE_WAITER, ROLE_ADMIN)
                        .requestMatchers(HttpMethod.DELETE, PRODUCT_URL + "/**", CATEGORY_URL + "/**").hasAnyAuthority(ROLE_WAITER, ROLE_ADMIN)

                        .requestMatchers(HttpMethod.POST, ORDER_URL, ORDER_ITEM_URL).hasAnyAuthority(ROLE_USER, ROLE_ADMIN)
                        .requestMatchers(HttpMethod.PUT, ORDER_URL, ORDER_ITEM_URL).hasAnyAuthority(ROLE_USER, ROLE_ADMIN)
                        .requestMatchers(HttpMethod.DELETE, ORDER_URL + "/**", ORDER_ITEM_URL + "/**").hasAnyAuthority(ROLE_USER, ROLE_ADMIN)

                        .requestMatchers(ORDER_URL + "/confirm", ORDER_URL + "/send").hasAnyAuthority(ROLE_WAITER, ROLE_ADMIN)

                        .anyRequest()
                        .authenticated()
                )

                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authenticationProvider(authenticationProvider)

                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                .logout(
                        logout -> logout.logoutUrl(LOGOUT_URL)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                );

        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }


}
