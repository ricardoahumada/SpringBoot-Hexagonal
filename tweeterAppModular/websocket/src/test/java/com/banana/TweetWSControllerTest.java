package com.banana;

import com.banana.config.WebSocketConfig;
import com.banana.model.TweetMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {WebSocketConfig.class}
)
@EnableAutoConfiguration
/*@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})*/
public class TweetWSControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Value("${local.server.port}")
    private int port;

    private String theme="hola";
    private String WEBSOCKET_URI;
    private String SEND_THEME_ENDPOINT = "/app/tweets/";
    private String SUBSCRIBE_THEME_ENDPOINT = "/topic/tweets/";

    CompletableFuture<TweetMessage> completableFuture;
    WebSocketStompClient stompClient;
    StompSession session;

    @Before
    public void setup() throws Exception {
        WEBSOCKET_URI="ws://localhost:"+port+"/tweets/";

        completableFuture = new CompletableFuture<>();
        stompClient = new WebSocketStompClient(new SockJsClient(createTransportClient()));
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        session = stompClient
                .connect(WEBSOCKET_URI, new StompSessionHandlerAdapter() {})
                .get(1, SECONDS);
        session.subscribe(SUBSCRIBE_THEME_ENDPOINT+theme, new DefaultStompFrameHandler());
    }

    @Test
    public void shouldSendTweetToServer() throws Exception {

        TweetMessage tweetMessage=new TweetMessage("Hola tweet websocket!",new Date(),1L, TweetMessage.MessageType.TWEET);
        session.send(SEND_THEME_ENDPOINT+theme, tweetMessage);
        assertTrue(session.isConnected());
    }

    @Test
    public void shouldReceiveTweetFromServer() throws Exception {
        TweetMessage tweetMessageRec =completableFuture.get(10, SECONDS);
        logger.info("TweetMessage received:"+tweetMessageRec);
        assertNotNull(tweetMessageRec);
    }

    @Test
    public void shouldDisconnect() throws Exception {
        session.disconnect();
        assertFalse(session.isConnected());
    }

    private List<Transport> createTransportClient() {
        List<Transport> transports = new ArrayList<>(1);
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        return transports;
    }

    class DefaultStompFrameHandler implements StompFrameHandler {
        @Override
        public Type getPayloadType(StompHeaders stompHeaders) {
            return TweetMessage.class;
        }

        @Override
        public void handleFrame(StompHeaders stompHeaders, Object o) {
            completableFuture.complete((TweetMessage) o);
        }
    }
}