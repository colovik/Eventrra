package com.example.repository;

import com.example.model.Enumerations.Role;
import com.example.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);

    User findByRole(Role role);

    List<User> findAllByRole(Role role);

    Optional<User> findById(String id);

}
