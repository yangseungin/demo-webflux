package com.webflux.controller;

import com.webflux.Service.UserService;
import com.webflux.domain.User;
import com.webflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @Autowired
    UserRepository repository;

    @Autowired
    private UserService userService;


    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getUserList() {
        List<User> all = repository.findAll();
        Flux<User> userFlux = Flux.fromIterable(all);
        return userFlux;
    }

    @GetMapping("/{userId}")
    public Mono<User> getUser(@PathVariable("userId") Long userId) {
        User user = repository.findById(userId).get();
        return Mono.just(user);
    }
    @GetMapping("/s/{userId}")
    public Mono<User> getUser2(@PathVariable("userId") Long userId) {
        User user = repository.findById(userId).get();
        return Mono.just(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> addUser(@RequestBody User user) {
        User save = repository.save(user);
        return Mono.just(save);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> delete(@PathVariable("userId") Long userId) {
        repository.deleteById(userId);
        return Mono.empty();
    }


}
