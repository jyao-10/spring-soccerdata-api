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
		Optional<Club> club = clubRepo.findById(id);

		if (club.isPresent()) {
			return ResponseEntity.ok(club.get());
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
			
	public ResponseEntity<Object> updateClub(Club club, Long id) {
		Optional<Club> clubToUpdate = clubRepo.findById(id);

		if (clubToUpdate.isPresent()) {
			club.setId(id);
			clubRepo.save(club);
			return ResponseEntity.ok(club);
		} else {
			throw new ResourceNotFoundException("Club id: " + id + " to update not found");
		}

	}

	public void deleteClub(Long id) {
		Optional<Club> clubToDelete = clubRepo.findById(id);
		
		if (clubToDelete.isPresent()) {
			for (League l : clubToDelete.get().getLeagues()) {
				l.getClubs().remove(clubToDelete.get());
				leagueRepo.save(l);
			}
			clubRepo.deleteById(id);
		}
	}

	public Set<Club> getAllClubsByLeagueId(Long id) {
		Optional<League> league = leagueRepo.findById(id);

		if (league.isPresent()) {
			return league.get().getClubs();
		} else {
			throw new ResourceNotFoundException("League id: " + id + " not found");
		}

	}
	
	
	public void addNewClub_ByLeague(Club club, Long league_id) {
		boolean clubFound = clubRepo.findAll().stream().
				anyMatch(c -> c.getId() == club.getId());

		if (!clubFound) {
			Optional<League> league = leagueRepo.findById(league_id);

			if (league.isPresent()) {
				Club c = clubRepo.save(club);

				league.get().getClubs().add(c);
				leagueRepo.save(league.get());
			} else {
				throw new ResourceNotFoundException("League id: " + league_id + " not found");
			}
		}
	}
	
	public void addLeagueToClub(Long club_id, Long league_id) {
		Optional<Club> club = clubRepo.findById(club_id);

		if (club.isPresent()) {
			Optional<League> league = leagueRepo.findById(league_id);
			
			if (league.isPresent()) {
				if (!club.get().getLeagues().contains(league.get())) {
					league.get().getClubs().add(club.get());
					clubRepo.save(club.get());
					leagueRepo.save(league.get());
				}
			} else {
				throw new ResourceNotFoundException("League id: " + league_id + " to add league not found");
			}
		} else {
			throw new ResourceNotFoundException("Club id: " + club_id + " to add league not found");
		}
	}
	
	public void removeLeagueFromClub(Long club_id, Long league_id) {
		Optional<Club> club = clubRepo.findById(club_id);

		if (club.isPresent()) {
			Optional<League> league = leagueRepo.findById(league_id);
			
			if (league.isPresent()) {
				if(club.get().getLeagues().contains(league.get())) {
					league.get().getClubs().remove(club.get());
					clubRepo.save(club.get());
					leagueRepo.save(league.get());
				} 
			} else {
				throw new ResourceNotFoundException("League id: " + league_id + " to add league not found");
			}
		} else {
			throw new ResourceNotFoundException("Club id: " + club_id + " to delete league not found");
		}
	}


}
