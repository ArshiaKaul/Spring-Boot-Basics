package com.arshia.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.arshia.demo.model.Alien;

public interface AlienRepo extends CrudRepository<Alien, Integer> {

}
