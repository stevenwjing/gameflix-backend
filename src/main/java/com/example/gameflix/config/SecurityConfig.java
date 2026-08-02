package com.example.gameflix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/api/register", "/api/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/", "/page/**", "/game/").permitAll()
                        .requestMatchers("/showNewGameForm", "/saveGame", "/showGameFormForUpdate/**", "/deleteGame/**").hasRole("ADMIN")
                        .requestMatchers("/memberList", "/showNewMemberForm", "/saveMember", "/showMemberFormForUpdate/**", "/deleteMember/**").hasRole("ADMIN")
                        .requestMatchers("/myGames/**").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
        return http.build();
    }
}
