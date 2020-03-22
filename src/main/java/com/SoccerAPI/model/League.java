package com.SoccerAPI.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class League {

	@Id
	@Column(name="league_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String country;
	
	@OneToMany(mappedBy = "league", targetEntity = Club.class, fetch = FetchType.EAGER)
	Set<Club> clubSet = new HashSet<>();
	
	public League() {
	
	}

	public League(Long id, String name, String country) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
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

	@Override
	public String toString() {
		return "League [id=" + id + ", name=" + name + ", country=" + country + "]";
	}
	
	
}
	