package com.example.service.impl;

import com.example.exceptions.NoSuchIDException;
import com.example.model.Band;
import com.example.repository.BandRepository;
import com.example.service.BandService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BandServiceImpl implements BandService {

    private final BandRepository bandRepository;

    public BandServiceImpl(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    @Override
    public List<Band> findAll() {
        return this.bandRepository.findAll();
    }

    @Override
    public Optional<Band> findById(Integer Id) {
        return Optional.ofNullable(this.bandRepository.findById(Id)
                .orElseThrow(() -> new NoSuchIDException(Id)));
    }

    @Override
    public boolean existsById(Integer id) {
        return this.bandRepository.existsById(id);
    }
}

