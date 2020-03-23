package com.SoccerAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.SoccerAPI.model.League;
import com.SoccerAPI.repository.LeagueRepository;
import com.SoccerAPI.service.LeagueService;

@RestController
public class LeagueController {
	
	
	@Autowired
	LeagueRepository repo;
	
	@Autowired
	LeagueService service;
	
	@GetMapping("/leagues")
	public List<League> getAllLeagues() {
		return service.getAllLeagues();
	}
	
	@GetMapping("/leagues/{id}")
	public ResponseEntity<Object> getLeagueById(@PathVariable Long id) {
		return service.getLeagueById(id);
	}
	
	@PostMapping("/leagues/addleague") 
	public void addLeague(@RequestBody League league) {
		service.addLeague(league);
	}
	
	@PutMapping("/leagues/updateleague/{id}")
	public void updateLeague(@RequestBody League league, @PathVariable Long id) {
		service.updateLeague(league, id);
	}
	
	@DeleteMapping("/leagues/deleteleague/{id}")
	public void deleteLeague(@PathVariable Long id) {
		service.deleteLeague(id);
	}

}
