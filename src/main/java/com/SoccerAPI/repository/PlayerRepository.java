package com.SoccerAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SoccerAPI.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

}
