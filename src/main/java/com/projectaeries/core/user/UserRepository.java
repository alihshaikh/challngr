package com.projectaeries.core.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
  @Query ("SELECT u from User u where u.email = :email")
  Optional<User> findByEmail(String email);
}
