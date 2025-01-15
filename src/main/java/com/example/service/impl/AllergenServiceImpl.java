package com.example.service.impl;

import com.example.model.Allergens;
import com.example.repository.AllergenRepository;
import com.example.service.AllergenService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllergenServiceImpl implements AllergenService {

    private final AllergenRepository allergenRepository;

    public AllergenServiceImpl(AllergenRepository allergenRepository) {
        this.allergenRepository = allergenRepository;
    }


    @Override
    public List<Allergens> findAll() {
        return this.allergenRepository.findAll();
    }

    @Override
    public List<Allergens> findAllById(List<Integer> allergens) {
        return this.allergenRepository.findAllById(allergens);
    }

    @Override
    public List<Allergens> findAllByFoodId(Integer id) {
        return this.allergenRepository.findAllAllergensByFoodId(id);
    }
}
