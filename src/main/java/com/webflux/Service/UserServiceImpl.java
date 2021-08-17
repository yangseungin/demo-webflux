package com.webflux.Service;

import com.webflux.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserServiceImpl {
    Flux<User> findAll();

    Mono<User> findById(Long id);

    Mono<User> create(User e);

    Mono<User> update(User e);

    Mono<Void> delete(Long id);

}
