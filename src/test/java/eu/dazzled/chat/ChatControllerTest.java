package eu.dazzled.chat;

import eu.dazzled.chat.model.ChatMessage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ChatControllerTest {

    private static RSocketRequester requester;

    @BeforeAll
    public static void setupOnce(
            @Autowired RSocketRequester.Builder builder,
            @Value("${spring.rsocket.server.port}") Integer port
    ) {
        String uri = "http://localhost:" + port + "/";
        requester = builder.websocket(URI.create(uri));
    }

    @Test
    void testRequestResponse() {
        Mono<ChatMessage> result = requester
                .route("ping")
                .data(new ChatMessage("test", "This is the test message"))
                .retrieveMono(ChatMessage.class);

        // Verify that the response message contains the expected data
        StepVerifier
                .create(result)
                .consumeNextWith(message -> {
                    assertThat(message.user()).isEqualTo("system");
                    assertThat(message.text()).isEqualTo("Re: [test] This is the test message");
                })
                .verifyComplete();
    }

}
