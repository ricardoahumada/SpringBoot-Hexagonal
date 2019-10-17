package es.bit.tweeterApp.websocket;

import es.bit.tweeterApp.websocket.model.TweetMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingDeque;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TweetWSControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Value("${local.server.port}")
    private int port;

    private String WEBSOCKET_URI;
    static final String WEBSOCKET_TOPIC = "/topic/tweets";
    static final String WEBSOCKET_ADD_USER = "/app/tweets.addUser";

    CompletableFuture<TweetMessage> completableFuture;
    WebSocketStompClient stompClient;
    StompSession session;

    @Before
    public void setup() throws Exception {
        WEBSOCKET_URI="ws://localhost:"+port+"/tweets";

        completableFuture = new CompletableFuture<>();
        stompClient = new WebSocketStompClient(new SockJsClient(createTransportClient()));
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        session = stompClient
                .connect(WEBSOCKET_URI, new StompSessionHandlerAdapter() {})
                .get(1, SECONDS);
        session.subscribe(WEBSOCKET_TOPIC, new DefaultStompFrameHandler());
    }

    @Test
    public void shouldSendTweetToServer() throws Exception {

        TweetMessage tweetMessage=new TweetMessage("Hola tweet websocket!",new Date(),1L, TweetMessage.MessageType.TWEET);
        session.send(WEBSOCKET_ADD_USER, tweetMessage);
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