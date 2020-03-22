package com.SoccerAPI.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Club {

	@Id
	@Column(name = "club_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String country;
	private String location;
	
	@ElementCollection
	private List<String> leagues_in;
	
	@ElementCollection
	private List<String> colors;
	
	@ElementCollection
	private List<String> honours;
	
	private String stadium;
	
	private String manager_name;
	private String manager_id;
	
	
	@ManyToOne
	@JoinColumn(name="league_id")
	private League league;
	
	public Club() {
		
	}

	public Club(Long id, String name, String country, String location, List<String> leagues_in, List<String> colors,
			List<String> honours, String stadium, String manager_name, String manager_id) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.location = location;
		this.leagues_in = leagues_in;
		this.colors = colors;
		this.honours = honours;
		this.stadium = stadium;
		this.manager_name = manager_name;
		this.manager_id = manager_id;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<String> getLeagues_in() {
		return leagues_in;
	}

	public void setLeagues_in(List<String> leagues_in) {
		this.leagues_in = leagues_in;
	}

	public List<String> getColors() {
		return colors;
	}

	public void setColors(List<String> colors) {
		this.colors = colors;
	}

	public List<String> getHonours() {
		return honours;
	}

	public void setHonours(List<String> honours) {
		this.honours = honours;
	}

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	public String getManager_name() {
		return manager_name;
	}

	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}

	public String getManager_id() {
		return manager_id;
	}

	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}

	@Override
	public String toString() {
		return "Club [id=" + id + ", name=" + name + ", country=" + country + ", location=" + location + ", leagues_in="
				+ leagues_in + ", colors=" + colors + ", honours=" + honours + ", stadium=" + stadium
				+ ", manager_name=" + manager_name + ", manager_id=" + manager_id;
	}
		
	
}
