package com.projectaeries.core.user;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    public User userDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        return user;
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDTO getUserById(Long id) {
        User foundUser = userRepository.findById(id).orElse(null);
        if (foundUser != null) {
            return userToUserDTO(foundUser);
        }
        return null;
    }

    public UserDTO createUser(UserDTO userDtoObject) {
        User creatingUser = new User();
        creatingUser.setFirstName(userDtoObject.getFirstName());
        creatingUser.setLastName(userDtoObject.getLastName());
        creatingUser.setEmail(userDtoObject.getEmail());
        userRepository.save(creatingUser);
        return userDtoObject;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
