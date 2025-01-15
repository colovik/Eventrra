package com.example.repository;

import com.example.model.Enumerations.Role;
import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    User findByRole(Role role);

    List<User> findAllByRole(Role role);
}