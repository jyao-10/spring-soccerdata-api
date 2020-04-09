package com.SoccerAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SoccerAPI.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
	public List<Player> findByPlayerName(String name);
	public List<Player> findByLastName(String lastName);
	
}
