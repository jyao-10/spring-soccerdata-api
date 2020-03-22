package com.SoccerAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SoccerAPI.model.League;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> { }
