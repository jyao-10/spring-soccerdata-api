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
	
	@GetMapping("/clubs/{club_id}")
	public ResponseEntity<Object> getClubById(@PathVariable Long club_id) {
		return service.getClubById(club_id);
	}
	
	@PostMapping("/clubs") 
	public void addClub(@RequestBody Club club) {
		service.addClub(club);
	}
	
	@PutMapping("/clubs/{club_id}")
	public void updateLeague(@RequestBody Club club, @PathVariable Long club_id) {
		service.updateClub(club, club_id);
	}
	
	@DeleteMapping("/clubs/{club_id}")
	public void deleteLeague(@PathVariable Long club_id) {
		service.deleteClub(club_id);
	}
	
	
	
}
