package com.banana.tweeterApp.internal.domain;

import org.junit.Before;
import org.junit.Test;

import javax.validation.*;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class UserTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void createUserNegative() throws ValidationException{
        User newUser = new User(null, null, null);
        Set<ConstraintViolation<User>> violations = validator.validate(newUser);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void createUserPositive() throws ValidationException{
        User newUser = new User("Ricardo","r@r.com","ppp");
        Set<ConstraintViolation<User>> violations = validator.validate(newUser);
        assertTrue(violations.isEmpty());
    }
}