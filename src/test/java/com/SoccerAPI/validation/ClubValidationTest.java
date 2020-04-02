package com.SoccerAPI.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.SoccerAPI.model.Club;
import com.SoccerAPI.types.Region;

public class ClubValidationTest {
	private static ValidatorFactory validatorFactory;
	private static Validator validator;
	
	@BeforeAll
	public static void createValidator() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}
	
	@AfterAll
	public static void close() {
		validatorFactory.close();
	}
	
	public Club getValidClub() {
		List<String> colors = new ArrayList<>();
		colors.add("blue");
		List<String> honours = new ArrayList<>();
		honours.add("champions");
		
		Club validClub = new Club(1L, "validClubName", "validCountry", Region.NORTH_AMERICA, "validLocation", colors, honours, "validStadium", "validManagerName");
		
		return validClub;
	}
	
	
	
	@Test
	public void testLeagueClub() {
		Club club = getValidClub();
		
		Set<ConstraintViolation<Club>> violations = validator.validate(club);
		
		assertTrue(violations.isEmpty());
		
	}
	
	@Test
	public void testInvalidName() {
		Club club = getValidClub();
		club.setName("");
		
		Set<ConstraintViolation<Club>> violations = validator.validate(club);
		
		assertEquals(violations.size(), 1);
		
		ConstraintViolation<Club> violation = violations.iterator().next();
		
		
		assertEquals("must not be blank", violation.getMessage());
		assertEquals("name", violation.getPropertyPath().toString());
		assertEquals("", violation.getInvalidValue());
	}
	
	@Test
	public void testInvalidCountry() {
		Club club = getValidClub();
		club.setCountry("");
		
		Set<ConstraintViolation<Club>> violations = validator.validate(club);
		
		assertEquals(violations.size(), 1);
		
		ConstraintViolation<Club> violation = violations.iterator().next();
		
		
		assertEquals("must not be blank", violation.getMessage());
		assertEquals("country", violation.getPropertyPath().toString());
		assertEquals("", violation.getInvalidValue());
	}
	
	@Test
	public void testInvalidLocation() {
		Club club = getValidClub();
		club.setLocation("");
		
		Set<ConstraintViolation<Club>> violations = validator.validate(club);
		
		assertEquals(violations.size(), 1);
		
		ConstraintViolation<Club> violation = violations.iterator().next();
		
		
		assertEquals("must not be blank", violation.getMessage());
		assertEquals("location", violation.getPropertyPath().toString());
		assertEquals("", violation.getInvalidValue());
	}
	
	@Test
	public void testInvalidStadium() {
		Club club = getValidClub();
		club.setStadium("");
		
		Set<ConstraintViolation<Club>> violations = validator.validate(club);
		
		assertEquals(violations.size(), 1);
		
		ConstraintViolation<Club> violation = violations.iterator().next();
		
		
		assertEquals("must not be blank", violation.getMessage());
		assertEquals("stadium", violation.getPropertyPath().toString());
		assertEquals("", violation.getInvalidValue());
	}
	
	@Test
	public void testInvalidManagerName() {
		Club club = getValidClub();
		club.setManagerName("");
		
		Set<ConstraintViolation<Club>> violations = validator.validate(club);
		
		assertEquals(violations.size(), 1);
		
		ConstraintViolation<Club> violation = violations.iterator().next();
		
		
		assertEquals("must not be blank", violation.getMessage());
		assertEquals("managerName", violation.getPropertyPath().toString());
		assertEquals("", violation.getInvalidValue());
	}
	
	
	@Test
	public void testInvalidSpacesName() {
		
		Club club = getValidClub();
		club.setName("Example Club");
		
		Set<ConstraintViolation<Club>> violations = validator.validate(club);
		
		assertEquals(violations.size(), 1);
		
		ConstraintViolation<Club> violation = violations.iterator().next();
		
		
		assertEquals("name", violation.getPropertyPath().toString());
		assertEquals("Example Club", violation.getInvalidValue());
		
		String regEx = "^[\\S]+$";
		String violationMessage = "must match " + "\"" + regEx + "\"";
		assertEquals(violationMessage, violation.getMessage());
		
	}
	
	@Test
	public void testInvalidSpacesCountry() {
		
		Club club = getValidClub();
		club.setCountry("Example Country");
		
		Set<ConstraintViolation<Club>> violations = validator.validate(club);
		
		assertEquals(violations.size(), 1);
		
		ConstraintViolation<Club> violation = violations.iterator().next();
		
		
		assertEquals("country", violation.getPropertyPath().toString());
		assertEquals("Example Country", violation.getInvalidValue());
		
		String regEx = "^[\\S]+$";
		String violationMessage = "must match " + "\"" + regEx + "\"";
		assertEquals(violationMessage, violation.getMessage());
		
	}
	
	@Test
	public void testInvalidSpacesLocation() {
		
		Club club = getValidClub();
		club.setLocation("Example Location");
		
		Set<ConstraintViolation<Club>> violations = validator.validate(club);
		
		assertEquals(violations.size(), 1);
		
		ConstraintViolation<Club> violation = violations.iterator().next();
		
		
		assertEquals("location", violation.getPropertyPath().toString());
		assertEquals("Example Location", violation.getInvalidValue());
		
		String regEx = "^[\\S]+$";
		String violationMessage = "must match " + "\"" + regEx + "\"";
		assertEquals(violationMessage, violation.getMessage());
		
	}
	
	@Test
	public void testInvalidSpacesStadium() {
		
		Club club = getValidClub();
		club.setStadium("Example Stadium");
		
		Set<ConstraintViolation<Club>> violations = validator.validate(club);
		
		assertEquals(violations.size(), 1);
		
		ConstraintViolation<Club> violation = violations.iterator().next();
		
		
		assertEquals("stadium", violation.getPropertyPath().toString());
		assertEquals("Example Stadium", violation.getInvalidValue());
		
		String regEx = "^[\\S]+$";
		String violationMessage = "must match " + "\"" + regEx + "\"";
		assertEquals(violationMessage, violation.getMessage());
		
	}
	
	@Test
	public void testInvalidSpacesMangerName() {
		
		Club club = getValidClub();
		club.setManagerName("Example managerName");
		
		Set<ConstraintViolation<Club>> violations = validator.validate(club);
		
		assertEquals(violations.size(), 1);
		
		ConstraintViolation<Club> violation = violations.iterator().next();
		
		
		assertEquals("managerName", violation.getPropertyPath().toString());
		assertEquals("Example managerName", violation.getInvalidValue());
		
		String regEx = "^[\\S]+$";
		String violationMessage = "must match " + "\"" + regEx + "\"";
		assertEquals(violationMessage, violation.getMessage());
		
	}
	
}
