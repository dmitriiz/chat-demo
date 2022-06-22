package eu.dazzled.chat;

import eu.dazzled.chat.model.ChatMessage;
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

    @MessageMapping("test")
    Mono<ChatMessage> handle(ChatMessage message) {
        return Mono.just(message);
    }

}
