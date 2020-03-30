package com.SoccerAPI.service;

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
		Optional<League> optionalLeague = leagueRepo.findById(id);

		if (optionalLeague.isPresent()) {
			return ResponseEntity.ok(optionalLeague.get());
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
		Optional<League> optionalLeague = leagueRepo.findById(id);

		if (optionalLeague.isPresent()) {
			league.setId(id);
			leagueRepo.save(league);
			return ResponseEntity.ok(league);
		} else {
			throw new ResourceNotFoundException("League id: " + id + " to update not found");
		}

	}

	public void deleteLeague(Long id) {
		leagueRepo.deleteById(id);
	}


	public ResponseEntity<Object> getLeaguesByClub(Long club_id) {
		Optional<Club> optionalClub = clubRepo.findById(club_id);

		if (optionalClub.isPresent()) {
			return ResponseEntity.ok(optionalClub.get().getLeagues_in());
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


}
