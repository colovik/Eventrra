package com.example.service.impl;

import com.example.exceptions.NoSuchIDException;
import com.example.model.Waiter;
import com.example.repository.WaiterRepository;
import com.example.service.WaiterService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WaiterServiceImpl implements WaiterService {

    private final WaiterRepository waiterRepository;

    public WaiterServiceImpl(WaiterRepository waiterRepository) {
        this.waiterRepository = waiterRepository;
    }

    @Override
    public Optional<Waiter> findById(Integer Id) {
        return Optional.ofNullable(this.waiterRepository.findById(Id)
                .orElseThrow(() -> new NoSuchIDException(Id)));
    }

    @Override
    public boolean existsById(Integer id) {
        return this.waiterRepository.existsById(id);
    }
}
