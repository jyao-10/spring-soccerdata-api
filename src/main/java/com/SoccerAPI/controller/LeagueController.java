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
	
	@GetMapping("/leagues/{league_id}")
	public ResponseEntity<Object> getLeagueById(@PathVariable Long league_id) {
		return service.getLeagueById(league_id);
	}
	
	
	@PostMapping("/leagues/addleague") 
	public void addLeague(@RequestBody League league) {
		service.addLeague(league);
	}
	
	@PutMapping("/leagues/updateleague/{league_id}")
	public void updateLeague(@RequestBody League league, @PathVariable Long league_id) {
		service.updateLeague(league, league_id);
	}
	
	@DeleteMapping("/leagues/deleteleague/{league_id}")
	public void deleteLeague(@PathVariable Long league_id) {
		service.deleteLeague(league_id);
	}
	
	

}
