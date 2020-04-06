package com.SoccerAPI.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "players")
public class Player {

	@Id
	@Column(name="player_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String player_name;
	private String firstName;
	private String lastName;
	private int age;
	private String birthDate;
	

	private String birthPlace;
	private String nationality;
	
	private String height;
	private String weight; 
	
	
	private int number;
	
	private String position;
	
	private boolean isInjured;
	
	@ManyToMany(mappedBy = "players", targetEntity = Club.class, fetch = FetchType.LAZY)
	private Set<Club> clubs = new HashSet<>();

	public Player() {}

	public Player(Long id, String player_name, String firstName, String lastName, int age, String birthDate,
			String birthPlace, String nationality, String height, String weight, int number, String position,
			boolean isInjured) {
		super();
		this.id = id;
		this.player_name = player_name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.birthDate = birthDate;
		this.birthPlace = birthPlace;
		this.nationality = nationality;
		this.height = height;
		this.weight = weight;
		this.number = number;
		this.position = position;
		this.isInjured = isInjured;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlayer_name() {
		return player_name;
	}

	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public boolean isInjured() {
		return isInjured;
	}

	public void setInjured(boolean isInjured) {
		this.isInjured = isInjured;
	}

	public Set<Club> getClubs() {
		return clubs;
	}

	public void setClubs(Set<Club> clubs) {
		this.clubs = clubs;
	}
	
	@Override
	public String toString() {
		return "Player [id=" + id + ", player_name=" + player_name + ", firstName=" + firstName + ", lastName="
				+ lastName + ", age=" + age + ", birthDate=" + birthDate + ", birthPlace=" + birthPlace
				+ ", nationality=" + nationality + ", height=" + height + ", weight=" + weight + ", number=" + number
				+ ", position=" + position + ", isInjured=" + isInjured + ", clubs=" + clubs + "]";
	}
	
	
}
