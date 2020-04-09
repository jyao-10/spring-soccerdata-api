package com.SoccerAPI.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.SoccerAPI.exception.ResourceNotFoundException;
import com.SoccerAPI.model.Club;
import com.SoccerAPI.model.Player;
import com.SoccerAPI.repository.ClubRepository;
import com.SoccerAPI.repository.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
	PlayerRepository playerRepo;
	
	@Autowired
	ClubRepository clubRepo;
	

	public List<Player> getAllPlayers() {
		return playerRepo.findAll();
	}
	
	public ResponseEntity<Object> getPlayerById(Long id) {
		Optional<Player> player = playerRepo.findById(id);

		if (player.isPresent()) {
			return ResponseEntity.ok(player.get());
		} else {
			throw new ResourceNotFoundException("Player id: " + id + " not found");
		}
	}
	
	public void addPlayer(Player player) {
		boolean playerFound = playerRepo.findAll().stream().
				anyMatch(c -> c.getId() == player.getId());

		if (!playerFound) {
			playerRepo.save(player);
		}
	}
			
	public ResponseEntity<Object> updatePlayer(Player player, Long id) {
		Optional<Player> playerToUpdate = playerRepo.findById(id);

		if (playerToUpdate.isPresent()) {
			player.setId(id);
			playerRepo.save(player);
			return ResponseEntity.ok(player);
		} else {
			throw new ResourceNotFoundException("Player id: " + id + " to update not found");
		}

	}

	public void deletePlayer(Long id) {
		Optional<Player> playerToDelete = playerRepo.findById(id);
		
		if (playerToDelete.isPresent()) {
			for (Club c : playerToDelete.get().getClubs()) {
				c.getPlayers().remove(playerToDelete.get());
				clubRepo.save(c);
			}
			playerRepo.deleteById(id);
		}
	}
	
	public Set<Player> getAllPlayersByClubId(Long id) {
		Optional<Club> club = clubRepo.findById(id);

		if (club.isPresent()) {
			return club.get().getPlayers();
		} else {
			throw new ResourceNotFoundException("Club id: " + id + " not found");
		}

	}
	
	public void addPlayerByClub(Player player, Long club_id) {
		boolean playerFound = playerRepo.findAll().stream().
				anyMatch(p -> p.getId() == player.getId());

		if (!playerFound) {
			Optional<Club> club = clubRepo.findById(club_id);

			if (club.isPresent()) {
				Player p = playerRepo.save(player);

				club.get().getPlayers().add(p);
				clubRepo.save(club.get());
			} else {
				throw new ResourceNotFoundException("Club id: " + club_id + " not found");
			}
		}
	}
	
	public void addClubToPlayer(Long player_id, Long club_id) {
		Optional<Player> player = playerRepo.findById(player_id);

		if (player.isPresent()) {
			Optional<Club> club = clubRepo.findById(club_id);
			
			if (club.isPresent()) {
				if (!player.get().getClubs().contains(club.get())) {
					club.get().getPlayers().add(player.get());
					playerRepo.save(player.get());
					clubRepo.save(club.get());
				}
			} else {
				throw new ResourceNotFoundException("Club id: " + club_id + " to add club not found");
			}
		} else {
			throw new ResourceNotFoundException("Player id: " + player_id + " to add club not found");
		}
	}
	
	public void removePlayerFromClub(Long player_id, Long club_id) {
		Optional<Player> player = playerRepo.findById(player_id);

		if (player.isPresent()) {
			Optional<Club> club = clubRepo.findById(club_id);
			
			if (club.isPresent()) {
				if (player.get().getClubs().contains(club.get())) {
					club.get().getPlayers().remove(player.get());
					playerRepo.save(player.get());
					clubRepo.save(club.get());
				}
			} else {
				throw new ResourceNotFoundException("Club id: " + club_id + " to remove club not found");
			}
		} else {
			throw new ResourceNotFoundException("Player id: " + player_id + " to remove club not found");
		}
	}
	
	
	public List<Player> searchByPlayer(String str) {
		if (!playerRepo.findByPlayerName(str).isEmpty()) {
			return playerRepo.findByPlayerName(str);
		}
		
		if(!playerRepo.findByLastName(str).isEmpty()) {
			return playerRepo.findByLastName(str);
		}
		
		return Collections.emptyList();
	}
	
	public Set<Player> getPlayersByClub(String club_name) {
		List<Club> clubs = clubRepo.findByName(club_name);
		
		Set<Player> players = new HashSet<>();
		
		if (!clubs.isEmpty()) {
			for (Club c : clubs) {
				players.addAll(c.getPlayers());
			}
		} else {
			throw new ResourceNotFoundException("Club name: " + club_name 
					+ " not found please try again or look at list of all clubs");
		}
		
		return players;
	}
	
}
