package com.latelier.catMash.controller;

import com.latelier.catMash.entities.Image;
import com.latelier.catMash.repository.CatRepository;
import com.latelier.catMash.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cats")
public class CatController {

    @Autowired
    private CatService catService;

    @GetMapping("/list")
    public Iterable<Image> list() {
        return catService.findAll();
    }
}
