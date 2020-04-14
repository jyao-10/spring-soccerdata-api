package com.SoccerAPI.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import com.SoccerAPI.types.Region;

@Entity
@Table(name = "clubs")
public class Club {

	@Id
	@Column(name = "club_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Pattern(regexp = "^[\\S]+$")
	private String name;
	
	@Pattern(regexp = "^[\\S]+$")
	private String country;
	
	@Enumerated(EnumType.STRING)
	private Region region;
	
	@Pattern(regexp = "^[\\S]+$")
	private String location;
	
	@Pattern(regexp = "^[\\S]+$")
	private String stadium;
	
	@Pattern(regexp = "^[\\S]+$")
	private String managerName;
	
	@ElementCollection
	private List<String> colors;
	
	@ElementCollection
	private List<String> honours;
	
	
	@ManyToMany(mappedBy = "clubs", targetEntity = League.class, fetch = FetchType.LAZY)
	private Set<League> leagues = new HashSet<>();
	
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name="clubs_players", 
			joinColumns = @ JoinColumn(name="club_id"),
			inverseJoinColumns = @JoinColumn(name="player_id"))
	private Set<Player> players = new HashSet<>();

	public Club() {}

	public Club(Long id, String name, String country, Region region, String location, List<String> colors,
			List<String> honours, String stadium, String managerName) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.region = region;
		this.location = location;
		this.colors = colors;
		this.honours = honours;
		this.stadium = stadium;
		this.managerName = managerName;
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
	
	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}


	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String manager_name) {
		this.managerName = manager_name;
	}


	public Set<League> getLeagues() {
		return leagues;
	}

	public void setLeagues(Set<League> leagues) {
		this.leagues = leagues;
	}
	
	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}
	
	@Override
	public String toString() {
		return "Club [id=" + id + ", name=" + name + ", country=" + country + ", location=" + location 
				 + ", colors=" + colors + ", honours=" + honours + ", stadium=" + stadium
				+ ", manager name=" + managerName;
	}
		
	
}

