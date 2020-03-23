package com.SoccerAPI.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.SoccerAPI.model.Club;
import com.SoccerAPI.repository.ClubRepository;

@Service
public class ClubService {

	
	@Autowired
	ClubRepository repo;
	
	public List<Club> getAllClubs() {
		return repo.findAll();
	}
	
	
	public ResponseEntity<Object> getClubById(Long id) {
		Optional<Club> optionalClub = repo.findById(id);
		
		if (optionalClub.isPresent()) {
			return ResponseEntity.ok(optionalClub.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Club Id: " + id + " not found");
		}
	}
	
	public void addClub(Club club) {
		boolean clubFound = repo.findAll().stream().
				anyMatch(c -> c.getId() == club.getId());
		
		if (!clubFound) {
			repo.save(club);
		}
	}
	
	public ResponseEntity<Object> updateClub(Club club, Long id) {
		Optional<Club> optionalClub = repo.findById(id);
		
		if (optionalClub.isPresent()) {
			club.setId(id);
			repo.save(club);
			return ResponseEntity.ok(club);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Club ID: " + id + " to update not found");
		}
		
	}
	
	public void deleteClub(Long id) {
		repo.deleteById(id);
	}
	
	
	
	
	
	
}
