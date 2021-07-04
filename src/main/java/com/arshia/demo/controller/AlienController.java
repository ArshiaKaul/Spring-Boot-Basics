package com.arshia.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arshia.demo.dao.AlienRepo;
import com.arshia.demo.model.Alien;

@Controller
public class AlienController {
	
	@Autowired
	AlienRepo repo;
	
	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}
	
	@RequestMapping("/addAlien")
	public String addAlien(Alien alien) {
		
		repo.save(alien);
		return "home.jsp";
	}
	
	//add support for xml in pom file
	//getAliens returns only in xml format
	//to return as json also, remove the 'produces' parameter or write produces={"application/xml", "application/json"}
	@RequestMapping(path="/aliens", produces= {"application/xml"})
	@ResponseBody
	public List<Alien> getAliens() {
		
		return repo.findAll();
	}
	
	//returns an alien in JSON format based on aid
	@RequestMapping("/aliens/{aid}")
	@ResponseBody
	public Optional<Alien> getAlien(@PathVariable("aid") int aid) {
		
		return repo.findById(aid);
	}
}
