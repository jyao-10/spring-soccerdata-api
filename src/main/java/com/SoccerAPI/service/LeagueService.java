package com.SoccerAPI.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.SoccerAPI.exception.ResourceNotFoundException;
import com.SoccerAPI.model.Club;
import com.SoccerAPI.model.League;
import com.SoccerAPI.repository.ClubRepository;
import com.SoccerAPI.repository.LeagueRepository;
import com.SoccerAPI.types.LeagueType;

@Service
public class LeagueService {

	@Autowired
	LeagueRepository leagueRepo;

	@Autowired
	ClubRepository clubRepo;

	public List<League> getAllLeagues() {
		return leagueRepo.findAll();
	}

	public ResponseEntity<Object> getLeagueById(Long id) {
		Optional<League> league = leagueRepo.findById(id);

		if (league.isPresent()) {
			return ResponseEntity.ok(league.get());
		} else {
			throw new ResourceNotFoundException("League id: " + id + " not found");
		}
	}
	

	public void addLeague(League league) {
		boolean leagueFound = leagueRepo.findAll().stream().
				anyMatch(c -> c.getId() == league.getId());

		if (!leagueFound) {
			leagueRepo.save(league);
		}
	}

	public ResponseEntity<Object> updateLeague(League league, Long id) {
		Optional<League> leagueToUpdate = leagueRepo.findById(id);

		if (leagueToUpdate.isPresent()) {
			league.setId(id);
			leagueRepo.save(league);
			return ResponseEntity.ok(league);
		} else {
			throw new ResourceNotFoundException("League id: " + id + " to update not found");
		}

	}

	public void deleteLeague(Long id) {
		Optional<League> leagueToDelete = leagueRepo.findById(id);
		
		if(leagueToDelete.isPresent()) {
			for (Club c : leagueToDelete.get().getClubs()) {
				c.getLeagues().remove(leagueToDelete.get());
				clubRepo.save(c);
			}
		}

		leagueRepo.deleteById(id);
	}


	public ResponseEntity<Object> getLeaguesByClub(Long club_id) {
		Optional<Club> club = clubRepo.findById(club_id);

		if (club.isPresent()) {
			return ResponseEntity.ok(club.get().getLeagues());
		} else {
			throw new ResourceNotFoundException("Club id: " + club_id + " not found");
		}

	}

	public List<League> getLeaguesBySearch(String str) {
		if (!leagueRepo.findByName(str).isEmpty()) {
			return leagueRepo.findByName(str);
		} else {
			return leagueRepo.findByCountry(str);
		}
	}
	
	public List<League> getLeaguesByType(String type) {
		if (type.equalsIgnoreCase("LEAGUE")) {
			return leagueRepo.findByType(LeagueType.LEAGUE);
		} else if (type.equalsIgnoreCase("CUP")) {
			return leagueRepo.findByType(LeagueType.CUP);
		}
		return Collections.emptyList();
	}


}
