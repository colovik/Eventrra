package com.example.repository;

import com.example.model.Catering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CateringRepository extends JpaRepository<Catering, Integer> {
    List<Catering> findAllByName (String name);
}
