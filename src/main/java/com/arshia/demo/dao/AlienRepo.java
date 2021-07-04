package com.arshia.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arshia.demo.model.Alien;


public interface AlienRepo extends JpaRepository<Alien, Integer> {
	
}
