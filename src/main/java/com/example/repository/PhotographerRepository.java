package com.example.repository;

import com.example.model.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotographerRepository extends JpaRepository<Photographer, Integer> {
    List<Photographer> findAll();

    boolean existsById(Integer id);

}
