package com.latelier.catMash.service;

import com.latelier.catMash.entities.Image;
import com.latelier.catMash.repository.CatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatService {
    private final CatRepository catRepository;

    public void saveAll(List<Image> paquet) {
        catRepository.saveAll(paquet);
    }

    public List<Image> findAll() {
        return catRepository.findAll();
    }
}
