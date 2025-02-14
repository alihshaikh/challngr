package com.projectaeries.core.oauth2.google;

import com.projectaeries.core.user.User;
import com.projectaeries.core.user.UserDTO;
import com.projectaeries.core.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class GoogleOAuth2UserService {
  Logger logger = LoggerFactory.getLogger(GoogleOAuth2UserService.class);
  private final UserRepository userRepository;

  public GoogleOAuth2UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void handleGoogleOAuth2UserCreation(UserDTO userDTO) throws IOException {
    if (this.findUserByEmail(userDTO.getEmail()).isPresent()) {
      logger.info("At user authentication: {} already exists.", userDTO.getEmail());
    }
    else {
      this.createGoogleOAuth2User(userDTO);
      logger.info("Created user: {}", userDTO.getEmail());
    }
  }

  public Optional<User> findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public void createGoogleOAuth2User(UserDTO userDtoObject) throws IOException {
    User creatingUser = new User();
    creatingUser.setFirstName(userDtoObject.getFirstName());
    creatingUser.setLastName(userDtoObject.getLastName());
    creatingUser.setEmail(userDtoObject.getEmail());
    userRepository.save(creatingUser);
  }
}
