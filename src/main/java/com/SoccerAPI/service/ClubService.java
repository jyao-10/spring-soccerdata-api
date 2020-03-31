package com.SoccerAPI.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.SoccerAPI.exception.ResourceNotFoundException;
import com.SoccerAPI.model.Club;
import com.SoccerAPI.model.League;
import com.SoccerAPI.repository.ClubRepository;
import com.SoccerAPI.repository.LeagueRepository;

@Service
public class ClubService {

	@Autowired
	LeagueRepository leagueRepo;

	@Autowired
	ClubRepository clubRepo;

	public List<Club> getAllClubs() {
		return clubRepo.findAll();
	}


	public ResponseEntity<Object> getClubById(Long id) {
		Optional<Club> optionalClub = clubRepo.findById(id);

		if (optionalClub.isPresent()) {
			return ResponseEntity.ok(optionalClub.get());
		} else {
			throw new ResourceNotFoundException("Club id: " + id + " not found");
		}
	}

	public void addClub(Club club) {
		boolean clubFound = clubRepo.findAll().stream().
				anyMatch(c -> c.getId() == club.getId());

		if (!clubFound) {
			clubRepo.save(club);
		}
	}

	public void addClubByLeague(Club club, Long id) {
		boolean clubFound = clubRepo.findAll().stream().
				anyMatch(c -> c.getId() == club.getId());

		if (!clubFound) {
			Optional<League> optionalLeague = leagueRepo.findById(id);

			if (optionalLeague.isPresent()) {
				Club c = clubRepo.save(club);

				optionalLeague.get().getClubs().add(c);
				leagueRepo.save(optionalLeague.get());
			} else {
				throw new ResourceNotFoundException("League id: " + id + " not found");
			}
		}
	}

	public ResponseEntity<Object> updateClub(Club club, Long id) {
		Optional<Club> optionalClub = clubRepo.findById(id);

		if (optionalClub.isPresent()) {
			club.setId(id);
			clubRepo.save(club);
			return ResponseEntity.ok(club);
		} else {
			throw new ResourceNotFoundException("Club id: " + id + " to update not found");
		}

	}

	public void deleteClub(Long id) {
		Optional<Club> OptionalClub = clubRepo.findById(id);
		
		if (OptionalClub.isPresent()) {
			for (League l : OptionalClub.get().getLeagues()) {
				l.getClubs().remove(OptionalClub.get());
				leagueRepo.save(l);
			}
			clubRepo.deleteById(id);
		}
	}

	public Set<Club> getAllClubsByLeagueId(Long id) {
		Optional<League> optionalLeague = leagueRepo.findById(id);

		if (optionalLeague.isPresent()) {
			return optionalLeague.get().getClubs();
		} else {
			throw new ResourceNotFoundException("League id: " + id + " not found");
		}

	}




}
