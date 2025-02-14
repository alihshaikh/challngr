package com.projectaeries.core.oauth2.google;

import com.projectaeries.core.user.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {
  private final GoogleOAuth2UserService googleOAuth2UserService;

  Logger logger = LoggerFactory.getLogger(GoogleOAuth2SuccessHandler.class);

  public GoogleOAuth2SuccessHandler(GoogleOAuth2UserService googleOAuth2UserService) {
    this.googleOAuth2UserService = googleOAuth2UserService;

  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Authentication authentication) throws IOException, ServletException
  {
    OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
    String first_name = oauth2User.getAttribute("given_name");
    String last_name = oauth2User.getAttribute("family_name");
    String email = oauth2User.getAttribute("email");

    logger.info("OAuth2 login successful: {}", email);

    UserDTO user = new UserDTO(first_name, last_name, email);
    googleOAuth2UserService.handleGoogleOAuth2UserCreation(user);
    response.sendRedirect("/");
  }
}
