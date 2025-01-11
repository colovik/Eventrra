package com.example.service.impl;

import com.example.exceptions.NoSuchIDException;
import com.example.exceptions.NoSuchUsernameException;
import com.example.model.Band;
import com.example.repository.BandRepository;
import com.example.service.BandService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Band> findById(Integer Id) {
        return Optional.ofNullable(this.bandRepository.findById(Id)
                .orElseThrow(() -> new NoSuchIDException(Id)));    }
}

