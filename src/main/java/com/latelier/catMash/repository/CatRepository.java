package com.latelier.catMash.repository;

import com.latelier.catMash.entities.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends CrudRepository<Image, String> {

    @Override
    List<Image> findAll();
}
