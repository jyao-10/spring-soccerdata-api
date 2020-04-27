package com.SoccerAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.SoccerAPI.model.Club;
import com.SoccerAPI.repository.LeagueRepository;
import com.SoccerAPI.service.StandingsService;

@RestController
public class StandingsController {
	
	@Autowired
	LeagueRepository repo;
	
	@Autowired
	StandingsService service;
	
	@GetMapping("/standings/{league_id}")
	public List<Club> getStandingsById(@PathVariable Long league_id) {
		return service.getStandingsById(league_id);
	}
	
	
}
