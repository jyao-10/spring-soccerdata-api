package com.SoccerAPI.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.SoccerAPI.exception.NoContentException;
import com.SoccerAPI.exception.ResourceNotFoundException;
import com.SoccerAPI.model.League;
import com.SoccerAPI.repository.LeagueRepository;

@Service
public class LeagueService {
	
	@Autowired
	LeagueRepository repo;
	
	public List<League> getAllLeagues() {
		return repo.findAll();
	}
	
	public ResponseEntity<Object> getLeagueById(Long id) {
		Optional<League> optionalLeague = repo.findById(id);
		
		if (optionalLeague.isPresent()) {
			return ResponseEntity.ok(optionalLeague.get());
		} else {
			throw new NoContentException("League id: " + id + " not found");
		}
	}
	
	public void addLeague(League league) {
		boolean leagueFound = repo.findAll().stream().
				anyMatch(c -> c.getId() == league.getId());
		
		if (!leagueFound) {
			repo.save(league);
		}
	}
	
	public ResponseEntity<Object> updateLeague(League league, Long id) {
		Optional<League> optionalLeague = repo.findById(id);
		
		if (optionalLeague.isPresent()) {
			league.setId(id);
			repo.save(league);
			return ResponseEntity.ok(league);
		} else {
			throw new ResourceNotFoundException("League id: " + id + " to update not found");
		}
		
	}
	
	public void deleteLeague(Long id) {
		repo.deleteById(id);
	}

}
