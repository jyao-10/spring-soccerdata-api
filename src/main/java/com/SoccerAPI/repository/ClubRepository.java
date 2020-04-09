package com.SoccerAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SoccerAPI.model.Club;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
	List<Club> findByName(String name);
}
