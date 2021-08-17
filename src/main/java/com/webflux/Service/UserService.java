package com.webflux.Service;

import com.webflux.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceImpl {
    private final WebClient webClient;

    @Override
    public Flux<User> findAll() {
        return webClient.get()
                .uri("/api/user")
                .retrieve()
                .bodyToFlux(User.class)
                .timeout(Duration.ofMillis(1_000));
    }

    @Override
    public Mono<User> findById(Long userId) {
        return webClient.get()
                .uri("/api/user/" + userId)
                .retrieve()
                .bodyToMono(User.class);
    }

    @Override
    public Mono<User> create(User e) {
        return webClient.post()
                .uri("/api/user")
                .body(Mono.just(e),User.class)
                .retrieve()
                .bodyToMono(User.class)
                .timeout(Duration.ofMillis(1_000));
    }

    @Override
    public Mono<User> update(User e) {
        return null;
    }

    @Override
    public Mono<Void> delete(Long userId) {
        return webClient.delete()
                .uri("/api/user/"+userId)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
