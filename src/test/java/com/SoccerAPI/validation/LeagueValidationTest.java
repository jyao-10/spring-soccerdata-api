package com.SoccerAPI.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.SoccerAPI.model.League;
import com.SoccerAPI.types.LeagueType;
import com.SoccerAPI.types.Region;

public class LeagueValidationTest {
	
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
	
	public League getValidLeague() {
		
		League validLeague = new League(1L, "test_League", "USA", Region.NORTH_AMERICA, LeagueType.LEAGUE);
		
		return validLeague;	
	}
	
	
	@Test
	public void testLeagueValid() {
		
		League league = getValidLeague();
		
		Set<ConstraintViolation<League>> violations = validator.validate(league);
		
		assertTrue(violations.isEmpty());
		
	}
	
	@Test
	public void testInvalidBlankName() {
		
		League league = getValidLeague();
		league.setName("");
		
		Set<ConstraintViolation<League>> violations = validator.validate(league);
		
		assertEquals(violations.size(), 1);
		
		ConstraintViolation<League> violation = violations.iterator().next();
		
		assertEquals("name", violation.getPropertyPath().toString());
		assertEquals("", violation.getInvalidValue());

	}
	
	@Test
	public void testInvalidBlankCountry() {
		
		League league = getValidLeague();
		league.setCountry("");
		
		Set<ConstraintViolation<League>> violations = validator.validate(league);
		
		assertEquals(violations.size(), 1);
		
		ConstraintViolation<League> violation = violations.iterator().next();
		
		assertEquals("country", violation.getPropertyPath().toString());
		assertEquals("", violation.getInvalidValue());
		
	}	
	
	@Test
	public void testInvalidSpacesName() {
		
		League league = getValidLeague();
		league.setName("Example League");
		
		Set<ConstraintViolation<League>> violations = validator.validate(league);
		
		assertEquals(violations.size(), 1);
		
		ConstraintViolation<League> violation = violations.iterator().next();
		
		
		assertEquals("name", violation.getPropertyPath().toString());
		assertEquals("Example League", violation.getInvalidValue());
		
		String regEx = "^[\\S]+$";
		String violationMessage = "must match " + "\"" + regEx + "\"";
		assertEquals(violationMessage, violation.getMessage());
		
	}
	
	@Test
	public void testInvalidSpacesCountry() {
		
		League league = getValidLeague();
		league.setCountry("Example Country");
		
		Set<ConstraintViolation<League>> violations = validator.validate(league);
		
		assertEquals(violations.size(), 1);
		
		ConstraintViolation<League> violation = violations.iterator().next();
		
		assertEquals("country", violation.getPropertyPath().toString());
		assertEquals("Example Country", violation.getInvalidValue());
		
		String regEx = "^[\\S]+$";
		String violationMessage = "must match " + "\"" + regEx + "\"";
		assertEquals(violationMessage, violation.getMessage());
		
	}
	
	
}
