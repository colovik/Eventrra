package com.example.service.impl;

import com.example.exceptions.NoSuchUsernameException;
import com.example.model.Band;
import com.example.repository.BandRepository;
import com.example.service.BandService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BandServiceImpl implements BandService{

    private final BandRepository bandRepository;

    public BandServiceImpl(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    @Override
    public List<Band> findAll() {
        return this.bandRepository.findAll();
    }

    @Override
    public Band findByName(String name) {
        return this.bandRepository.findAllByName(name).stream().findFirst()
                .orElseThrow(NoSuchUsernameException::new);
    }
}

