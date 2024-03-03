package com.example.service.impl;

import com.example.exceptions.NoSuchUsernameException;
import com.example.model.Photographer;
import com.example.repository.PhotographerRepository;
import com.example.service.PhotographerService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Photographer findByName(String name) {
        return this.photographerRepository.findAllByName(name).stream().findFirst()
                .orElseThrow(NoSuchUsernameException::new);
    }
}
