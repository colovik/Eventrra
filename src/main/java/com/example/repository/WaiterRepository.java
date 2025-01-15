package com.example.repository;

import com.example.model.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaiterRepository extends JpaRepository<Waiter, Integer> {
    boolean existsById(Integer id);

}
