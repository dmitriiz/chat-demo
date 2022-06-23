package eu.dazzled.chat;

import eu.dazzled.chat.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.annotation.ConnectMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class ChatController {

//    @ConnectMapping
//    Mono<Void> handle(RSocketRequester requester) {
//        requester.route("status").data("5")
//                .retrieveFlux(ChatMessage.class)
//                .subscribe(message -> {
//                    // ...
//                });
//        return requester.rsocket().onClose();
//    }

    @MessageMapping("ping")
    Mono<ChatMessage> handlePing(ChatMessage message) {
        return Mono.just(new ChatMessage("system", "Re: [" + message.user() + "] " + message.text()));
    }

    @MessageExceptionHandler
    public Mono<ChatMessage> handleException(Exception e) {
        return Mono.just(new ChatMessage("system", "Exception: " + e.getMessage()));
    }
}
