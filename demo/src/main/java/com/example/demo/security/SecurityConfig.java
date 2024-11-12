package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthEntryPoint jwtAuthEntryPoint;
    
    @Bean 
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
            csrf(AbstractHttpConfigurer::disable)
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions
                    .disable()
                )
            )
            .authorizeHttpRequests(requests -> requests
            .requestMatchers("h2/**").permitAll()
            .requestMatchers("/usuario/login").permitAll()
            .requestMatchers("/veterinario/login").permitAll()
            .requestMatchers("veterinario/details").hasAnyAuthority("Veterinario","Admin")
            .requestMatchers("usuario/details").hasAnyAuthority("Admin", "Veterinario", "Usuario")
            .requestMatchers("/usuario/all").hasAnyAuthority("Admin","Veterinario")
            .requestMatchers("/veterinario/").authenticated()
            //requestMatchers("/usuario/add").hasAnyAuthority("Admin","Veterinario")
            .requestMatchers("/usuario/update").hasAnyAuthority("Admin","Veterinario")
            .requestMatchers("/usuario/delete").hasAnyAuthority("Admin","Veterinario")
            .requestMatchers("/veterinario/all").hasAnyAuthority("Admin","Veterinario")
            .requestMatchers("/veterinario/add").hasAuthority("Admin")
            .requestMatchers("/veterinario/update").hasAuthority("Admin")
            .requestMatchers("/veterinario/delete").hasAuthority("Admin")
            .requestMatchers("/usuario/all").hasAnyAuthority("Admin","Veterinario")
            .requestMatchers("/mascota/**").hasAnyAuthority("Veterinario","Admin")
            .requestMatchers("/admin/dashboard").hasAuthority("Admin")
            .requestMatchers("/admin/login").permitAll()

            .anyRequest().permitAll()
            )
        .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthEntryPoint));

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    

    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticationConfiguration
    )throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }

}
