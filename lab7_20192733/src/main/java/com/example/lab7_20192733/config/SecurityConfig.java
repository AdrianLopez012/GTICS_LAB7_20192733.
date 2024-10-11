package com.example.lab7_20192733.config;

import com.example.lab7_20192733.entity.Users;
import com.example.lab7_20192733.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import java.util.Collections;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                                    .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/login").permitAll()
                                                                      .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                                   .requestMatchers("/gerente/**").hasAuthority("ROLE_GERENTE")
                        .requestMatchers("/cliente/**").hasAuthority("ROLE_CLIENTE")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                             .loginPage("/login")
                        .defaultSuccessUrl("/cliente/reservas", true)
                        .permitAll()
                )
                                        .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            Users user = userRepository.findByEmail(username).orElseThrow(() ->
                    new UsernameNotFoundException("Usuario no encontrado con el email: " + username));


            return new User(
                    user.getEmail(),
                    user.getPassword(),
                    Collections.singleton(new org.springframework.security.core.authority.SimpleGrantedAuthority(user.getRole().getName()))
            );
        };
    }
}
