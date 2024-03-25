package org.example.first_spring_boot_project.config;


import lombok.RequiredArgsConstructor;
import org.example.first_spring_boot_project.enumeration.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                .cors(withDefaults()).csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests((auth) -> auth.requestMatchers("/", "/index.html").permitAll().anyRequest().authenticated()).httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder().username("user").password(passwordEncoder.encode("password")).authorities(Role.USER.getGrantedAuthorities()).build();
        UserDetails todo = User.builder().username("todo").password(passwordEncoder.encode("password")).authorities(Role.ADMIN.getGrantedAuthorities()).build();
        return new InMemoryUserDetailsManager(user, todo);
    }

}
