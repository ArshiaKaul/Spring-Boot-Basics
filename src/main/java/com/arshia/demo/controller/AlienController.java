package com.arshia.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arshia.demo.dao.AlienRepo;
import com.arshia.demo.model.Alien;

@RestController
public class AlienController {
	
	@Autowired
	AlienRepo repo;
	
	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}
	
	@PostMapping(path="/alien", consumes= {"application/json"})
	public Alien addAlien(@RequestBody Alien alien) {
		
		repo.save(alien);
		return alien;
	}
	
	//add support for xml in pom file
	//getAliens returns only in xml format
	//to return as json also, remove the 'produces' parameter or write produces={"application/xml", "application/json"}
	@GetMapping(path="/aliens")
	public List<Alien> getAliens() {
		
		return repo.findAll();
	}
	
	//returns an alien in JSON format based on aid
	@RequestMapping("/alien/{aid}")
	@ResponseBody
	public Optional<Alien> getAlien(@PathVariable("aid") int aid) {
		
		return repo.findById(aid);
	}
}
