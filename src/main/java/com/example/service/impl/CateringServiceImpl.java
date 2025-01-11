package com.example.service.impl;

import com.example.exceptions.NoSuchIDException;
import com.example.exceptions.NoSuchUsernameException;
import com.example.model.Catering;
import com.example.repository.CateringRepository;
import com.example.service.CateringService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CateringServiceImpl implements CateringService {

    private final CateringRepository cateringRepository;

    public CateringServiceImpl(CateringRepository cateringRepository) {
        this.cateringRepository = cateringRepository;
    }

    @Override
    public List<Catering> findAll() {
        return this.cateringRepository.findAll();
    }

    @Override
    public Catering findByName(String name) {
        return this.cateringRepository.findAllByName(name).stream().findFirst()
                .orElseThrow(NoSuchUsernameException::new);
    }

    @Override
    public Optional<Catering> findById(Integer Id) {
        return Optional.ofNullable(this.cateringRepository.findById(Id)
                .orElseThrow(() -> new NoSuchIDException(Id)));
    }
}
