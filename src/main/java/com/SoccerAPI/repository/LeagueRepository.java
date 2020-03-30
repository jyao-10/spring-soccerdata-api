package com.SoccerAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SoccerAPI.model.League;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {
	public List<League> findByName(String name);
	public List<League> findByCountry(String country);
	
}
