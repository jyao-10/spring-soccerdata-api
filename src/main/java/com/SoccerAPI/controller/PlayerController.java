package com.SoccerAPI.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.SoccerAPI.model.Player;
import com.SoccerAPI.repository.PlayerRepository;
import com.SoccerAPI.service.PlayerService;

@RestController
public class PlayerController {

	@Autowired
	PlayerRepository repo;
	
	@Autowired
	PlayerService service;
	
	@GetMapping("/players")
	public List<Player> getAllPlayers() {
		return service.getAllPlayers();
	}
	
	@GetMapping("/players/{player_id}")
	public ResponseEntity<Object> getPlayerById(@PathVariable Long player_id) {
		return service.getPlayerById(player_id);
	}
	
	@PostMapping("/players") 
	public void addPlayer(@RequestBody Player player) {
		service.addPlayer(player);
	}
	
	@PutMapping("/players/{player_id}")
	public void updatePlayer(@RequestBody Player player, @PathVariable Long player_id) {
		service.updatePlayer(player, player_id);
	}
	
	@DeleteMapping("/players/{player_id}")
	public void deletePlayer(@PathVariable Long player_id) {
		service.deletePlayer(player_id);
	}
	
	@GetMapping("players/club/{club_id}")
	public Set<Player> getAllPlayersByClubId(@PathVariable Long club_id) {
		return service.getAllPlayersByClubId(club_id);
	}
	
	@PostMapping("/players/club/{club_id}")
	public void addPlayerByClub(@RequestBody Player player, @PathVariable Long club_id) {
		service.addPlayerByClub(player, club_id);
	}
	
	@PutMapping("/players/addplayer/{player_id}/{club_id}")
	public void addClubToPlayer(@PathVariable Long player_id, @PathVariable Long club_id) {
		service.addClubToPlayer(player_id, club_id);
	}
	
	@PutMapping("/players/removeplayer/{player_id}/{club_id}")
	public void removePlayerFromClub(@PathVariable Long player_id, @PathVariable Long club_id) {
		service.removePlayerFromClub(player_id, club_id);
	}
	
	@GetMapping("/players/search/{player}")
	public List<Player> searchByPlayer(@PathVariable String player) {
		return service.searchByPlayer(player);
	}
	
	@GetMapping("/players/search/club/{club}")
	public Set<Player> getPlayersByClub(@PathVariable String club) {
		return service.getPlayersByClub(club);
	}
	
}
