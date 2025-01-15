package com.example.repository;

import com.example.model.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandRepository extends JpaRepository<Band, Integer> {
    List<Band> findAllByName (String name);

    boolean existsById(Integer id);

}
