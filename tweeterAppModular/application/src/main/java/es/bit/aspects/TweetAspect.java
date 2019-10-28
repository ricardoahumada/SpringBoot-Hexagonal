package es.bit.tweeterApp.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class TweetAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //execution(* PACKAGE.*.*(..))
    @Before("execution(* es.bit.tweeterApp.driven_adapter.*.*(..))")
    public void before(JoinPoint joinPoint){
        //Advice
        logger.info(" Check for tweet access ");
        logger.info(" Allowed execution for {}", joinPoint);
    }

    @After("execution(* es.bit.tweeterApp.driven_adapter.*.*(..))")
    public void after(JoinPoint joinPoint){
        //Advice
        logger.info(" Check for tweet output");
    }

    @AfterReturning(value = "execution(* es.bit.tweeterApp.driven_adapter.*.*(..))",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        logger.info("{} returned with value {}", joinPoint, result);
    }

    @AfterReturning(value = "execution(* es.bit.TweetController.addTweet(..))",
            returning = "result")
    public void afterReturningAddTweet(JoinPoint joinPoint, Object result) {
        logger.info("{} returned with value {}", joinPoint, result);
        //messagingTemplate.convertAndSend("/topic/tweets", TweetMapper.INSTANCE.tweetDtoTotweetMessage(tweetDto));

    }
}
