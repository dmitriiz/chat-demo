package eu.dazzled.chat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class TestController {
    @GetMapping
    Mono<String> test() {
        return Mono.just("OK");
    }
}
