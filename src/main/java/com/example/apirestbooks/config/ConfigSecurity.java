package com.example.apirestbooks.config;

import com.example.apirestbooks.filter.JwtReqFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
public class ConfigSecurity {
    @Autowired
    @Lazy
    private JwtReqFilter jwtReqFilter;

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(configure ->
                        configure
                                .requestMatchers(HttpMethod.GET, "/v1/libros").hasRole("Empleado")
                                .requestMatchers(HttpMethod.GET, "/v1/libros/**").hasRole("Empleado")
                                .requestMatchers(HttpMethod.POST, "/v1/libros").hasRole("Jefe")
                                .requestMatchers(HttpMethod.PUT, "/v1/libros/**").hasRole("Jefe")
                                .requestMatchers(HttpMethod.DELETE, "/v1/libros/**").hasRole("Jefe")
                                //.requestMatchers(HttpMethod.GET, "/v1/categorias").hasRole("Empleado")
                                .requestMatchers(HttpMethod.GET, "/v1/categorias/**").hasRole("Empleado")
                                .requestMatchers(HttpMethod.POST, "/v1/categorias").hasRole("Jefe")
                                .requestMatchers(HttpMethod.PUT, "/v1/categorias/**").hasRole("Jefe")
                                .requestMatchers(HttpMethod.DELETE, "/v1/categorias/**").hasRole("Jefe")
                                .requestMatchers("/v1/categorias", "/v1/authenticate", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                ).addFilterBefore(jwtReqFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /*
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails diego = User.builder()
                .username("dieAng")
                .password("{noop}1234")
                .roles("Empleado")
                .build();

        UserDetails pepe = User.builder()
                .username("pep1")
                .password("{noop}1234")
                .roles("Empleado", "Jefe")
                .build();

        return new InMemoryUserDetailsManager(diego, pepe);
    }
    */
}
