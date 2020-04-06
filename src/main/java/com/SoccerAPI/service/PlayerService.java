package com.SoccerAPI.service;

import java.util.List;
import java.util.Optional;

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
	
	
}
