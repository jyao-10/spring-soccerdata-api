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
	
	@GetMapping("/leagues/league/{league_id}")
	public ResponseEntity<Object> getLeagueById(@PathVariable Long league_id) {
		return service.getLeagueById(league_id);
	}

	
	@PostMapping("/leagues") 
	public void addLeague(@RequestBody League league) {
		service.addLeague(league);
	}
	
	@PutMapping("/leagues/{league_id}")
	public void updateLeague(@RequestBody League league, @PathVariable Long league_id) {
		service.updateLeague(league, league_id);
	}
	
	@DeleteMapping("/leagues/{league_id}")
	public void deleteLeague(@PathVariable Long league_id) {
		service.deleteLeague(league_id);
	}
	
	
	@GetMapping("/leagues/club/{club_id}")
	public ResponseEntity<Object> getLeaguesByClub(@PathVariable Long club_id) {
		return service.getLeaguesByClub(club_id);
	}
	
	@GetMapping("/leagues/search/{search_str}")
	public List<League> getLeaguesBySearch(@PathVariable String search_str) { 
		return service.getLeaguesBySearch(search_str);
	}
	
	@GetMapping("/leagues/type/{league_type}")
	public List<League> getLeaguesByType(@PathVariable String league_type) {
		return service.getLeaguesByType(league_type);
	}

}
