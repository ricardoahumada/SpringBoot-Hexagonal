'use strict';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];


function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/tweets', onMessageReceived);
    connectingElement.classList.add('hidden');
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    if(message.type=='TWEET'){
        var messageElement = document.createElement('li');

        var textElement = document.createElement('p');
        var messageText = document.createTextNode(message.texto);
        textElement.appendChild(messageText);

        messageElement.appendChild(textElement);

        messageArea.appendChild(messageElement);
        messageArea.scrollTop = messageArea.scrollHeight;
    }else{
        //nothing
    }
}

document.querySelector('#userid-button').onclick=(function(event){
    event.preventDefault();
    username = document.querySelector('#name').value.trim();

    if(username) {
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        var socket = new SockJS('http://localhost:8080/tweets');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
});

document.querySelector('#message-button').onclick=function(event){
    event.preventDefault();
    var messageContent = messageInput.value.trim();
    if(messageContent && stompClient) {

        var today = new Date();

        var tweetMessage = {
            autor: username,
            texto: messageInput.value,
            type: 'TWEET',
            fecha: today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate()
        };
        stompClient.send("/app/tweets.sendMessage", {}, JSON.stringify(tweetMessage));
        messageInput.value = '';
    }
}