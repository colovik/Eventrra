package com.example.repository;

import com.example.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    Optional<Location> findById(Integer id);

    void deleteById(Integer id);

    Location findByName(String location);
}
