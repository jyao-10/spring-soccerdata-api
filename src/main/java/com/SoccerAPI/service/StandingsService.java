package com.SoccerAPI.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SoccerAPI.exception.ResourceNotFoundException;
import com.SoccerAPI.model.Club;
import com.SoccerAPI.model.League;
import com.SoccerAPI.repository.LeagueRepository;

@Service
public class StandingsService {
	
	@Autowired
	LeagueRepository repo;
	
	public List<Club> getStandingsById(Long id) {
		Optional<League> league = repo.findById(id);
		
		if (league.isPresent()) {
			List<Club> table = league.get().getClubs().stream()
					.sorted(Comparator.comparing(Club::getPoints).reversed())
					.collect(Collectors.toList());
			
			return table;
		} else {
			throw new ResourceNotFoundException("League id: " + id + " not found,"
				+ " please look at available league ids");
			
		}
	}
}
