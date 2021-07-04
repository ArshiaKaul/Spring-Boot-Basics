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
	
	@RequestMapping("/getAlienById")
	public ModelAndView getAlienbyId(@RequestParam int aid) {
		
		ModelAndView mv = new ModelAndView("showAlien.jsp");
		Alien alien = repo.findById(aid).orElse(new Alien());
		mv.addObject(alien);
		return mv;
	}
	
	@RequestMapping("/getAlienByTech")
	public ModelAndView getAlienByTech(@RequestParam int aid) {
		
		ModelAndView mv = new ModelAndView("showAlien.jsp");
		Alien alien = repo.findById(aid).orElse(new Alien());
// 		Other ways to fetch aliens (only in console)		
		System.out.println(repo.findByTech("RedBull"));	//get aliens having tech=RedBull
		System.out.println(repo.findByAidGreaterThan(102)); //get aliens having ids>102
		System.out.println(repo.findByTechSorted("RedBull")); //get aliens having tech=RedBull in sorted order of aname
		
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
