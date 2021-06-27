package com.arshia.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping("/getAlien")
	public ModelAndView getAlien(@RequestParam int aid) {
		
		ModelAndView mv = new ModelAndView("showAlien.jsp");
		Alien alien = repo.findById(aid).orElse(new Alien());
		mv.addObject(alien);
		return mv;
	}
	
	@RequestMapping("/deleteAlien")
	public String deleteAlien(@RequestParam int aid) {
		
		Alien alien = repo.findById(aid).orElse(new Alien());
		repo.delete(alien);
		return "home.jsp";
	}
	
	@RequestMapping("/updateAlien")
	public String updateAlien(@RequestParam int aid, String aname, String tech) {
		
		Alien alien = repo.findById(aid).orElse(new Alien());
		alien.setAid(aid);
		alien.setAname(aname);
		alien.setTech(tech);
		repo.save(alien);
		return "home.jsp";
	}
}
