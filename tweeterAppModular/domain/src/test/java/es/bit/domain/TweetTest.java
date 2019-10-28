package es.bit.domain;

import es.bit.internal.domain.Tweet;
import es.bit.internal.domain.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class TweetTest {
    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void createTweetNegative(){
        Tweet newTweet = new Tweet(null, null);
        Set<ConstraintViolation<Tweet>> violations = validator.validate(newTweet);
        System.out.println("Violations:"+violations);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void createTweetPositive(){
        Tweet newTweet = new Tweet("Nuevo tweet", new User("Ricardo","r@r.com","ppp"));
        Set<ConstraintViolation<Tweet>> violations = validator.validate(newTweet);
        System.out.println("Violations:"+violations);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void createTweetSizeNegative(){
        String texto = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent lacinia tortor vel eros congue, sed tempor mi finibus. Nam faucibus ac massa ac eleifend. Integer ultricies purus at libero faucibus scelerisque. Nulla facilisi. Nullam et nisi libero. Ut ultrices elit auctor, tincidunt odio at, tempus libero. Cras vulputate augue sed neque posuere, non vulputate sapien imperdiet. Fusce malesuada euismod turpis, nec egestas augue rhoncus vitae. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus";
        Tweet newTweet = new Tweet(texto, new User("Ricardo","r@r.com","ppp"));
        Set<ConstraintViolation<Tweet>> violations = validator.validate(newTweet);
        System.out.println("Violations:"+violations);
        assertFalse(violations.isEmpty());
    }
}