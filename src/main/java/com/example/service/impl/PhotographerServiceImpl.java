package com.example.service.impl;

import com.example.exceptions.NoSuchIDException;
import com.example.model.Photographer;
import com.example.repository.PhotographerRepository;
import com.example.service.PhotographerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotographerServiceImpl implements PhotographerService {

    private final PhotographerRepository photographerRepository;

    public PhotographerServiceImpl(PhotographerRepository photographerRepository) {
        this.photographerRepository = photographerRepository;
    }

    @Override
    public List<Photographer> findAll() {
        return this.photographerRepository.findAll();
    }

    @Override
    public Optional<Photographer> findById(Integer Id) {
        return Optional.ofNullable(this.photographerRepository.findById(Id)
                .orElseThrow(() -> new NoSuchIDException(Id)));
    }

    @Override
    public boolean existsById(Integer id) {
        return this.photographerRepository.existsById(id);
    }
}
