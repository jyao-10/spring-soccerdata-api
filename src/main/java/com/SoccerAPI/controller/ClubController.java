package com.SoccerAPI.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.SoccerAPI.model.Club;
import com.SoccerAPI.repository.ClubRepository;
import com.SoccerAPI.service.ClubService;

@RestController
public class ClubController {

	@Autowired
	ClubRepository repo;
	
	@Autowired
	ClubService service;
	
	
	@GetMapping("/clubs")
	public List<Club> getAllClubs () {
		return service.getAllClubs();
	}
	
	@GetMapping("/clubs/club/{club_id}")
	public ResponseEntity<Object> getClubById(@PathVariable Long club_id) {
		return service.getClubById(club_id);
	}
	
	@PostMapping("/clubs") 
	public void addClub(@Valid @RequestBody Club club) {
		service.addClub(club);
	}
	
	@PutMapping("/clubs/{club_id}")
	public void updateClub(@Valid @RequestBody Club club, @PathVariable Long club_id) {
		service.updateClub(club, club_id);
	}
	
	@DeleteMapping("/clubs/{club_id}")
	public void deleteClub(@PathVariable Long club_id) {
		service.deleteClub(club_id);
	}
	
	
	@GetMapping("/clubs/league/{league_id}")
	public Set<Club> getAllClubsByLeagueId(@PathVariable Long league_id) {
		return service.getAllClubsByLeagueId(league_id);
	}
	
	@PostMapping("/clubs/league/{league_id}")
	public void addClubByLeague(@RequestBody Club club, @PathVariable Long league_id) {
		service.addNewClub_ByLeague(club, league_id);
	}
	
	@PutMapping("/clubs/addleague/{club_id}/{league_id}")
	public void addLeagueToClub(@PathVariable Long club_id, @PathVariable Long league_id) {
		service.addLeagueToClub(club_id, league_id);
	}
	
	@PutMapping("/clubs/removeleague/{club_id}/{league_id}")
	public void removeLeagueFromClub(@PathVariable Long club_id, @PathVariable Long league_id) {
		service.removeLeagueFromClub(club_id, league_id);
	}
	
}
