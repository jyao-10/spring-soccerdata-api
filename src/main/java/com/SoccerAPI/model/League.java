package com.SoccerAPI.model;

import java.beans.Transient;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.SoccerAPI.types.LeagueType;

@Entity
@Table(name = "leagues")
public class League {

	@Id
	@Column(name="league_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String country;
	
	@Enumerated(EnumType.STRING)
	private LeagueType type;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="leagues_clubs",
			joinColumns = @ JoinColumn(name="club_id"),
			inverseJoinColumns = @JoinColumn(name="league_id"))
	private Set<Club> clubs = new HashSet<>();
	
	
	public League() {
	
	}

	public League(Long id, String name, String country, LeagueType type) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.type = type;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public LeagueType getType() {
		return type;
	}

	public void setType(LeagueType type) {
		this.type = type;
	}

	@Transient
	public Set<Club> getClubs() {
		return clubs;
	}
	
	public void setClubs(Set<Club> clubs) {
		this.clubs = clubs;
	}
	
	@Override
	public String toString() {
		return "League [id=" + id + ", name=" + name + ", country=" + country + "]";
	}
	
	
}