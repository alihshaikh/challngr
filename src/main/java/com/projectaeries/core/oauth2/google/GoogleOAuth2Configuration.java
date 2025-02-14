package com.projectaeries.core.oauth2.google;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class GoogleOAuth2Configuration {

  private final GoogleOAuth2SuccessHandler successHandler;

  public GoogleOAuth2Configuration(GoogleOAuth2SuccessHandler successHandler) {
    this.successHandler = successHandler;
  }

@Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http

            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/", "/login/**", "/oauth2/**", "/api/**").permitAll()
                    .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                    .successHandler(successHandler)  // Calls success handler

            )
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "OAuth_Token")
            )
            .csrf(csrf -> csrf.disable());

    return http.build();
  }
}