package dev.gustavoteixeira.easygest.adapter.primary.http.user;

import dev.gustavoteixeira.easygest.model.user.User;
import dev.gustavoteixeira.easygest.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserHttpAdapter {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public Mono<String> create(@RequestBody Mono<NewUserRequest> newUser) {
        log.info("Request to create a new user received.");

        return newUser.map(nur -> User.builder()
                        .fullName(nur.getFullName())
                        .username(nur.getUsername())
                        .password(passwordEncoder.encode(nur.getPassword()))
                        .email(nur.getEmail())
                        .roles(List.of("REGULAR_USER"))
                        .build())
                .flatMap(userRepository::insert)
                .map(User::getId)
                .log();
    }

    @PostMapping("/partners")
    public Mono<String> createPartner(@RequestBody Mono<NewUserRequest> newUser) {
        log.info("Request to create a new user received.");

        return newUser.map(nur -> User.builder()
                        .fullName(nur.getFullName())
                        .username(nur.getUsername())
                        .password(passwordEncoder.encode(nur.getPassword()))
                        .email(nur.getEmail())
                        .roles(List.of("PARTNER"))
                        .build())
                .flatMap(userRepository::insert)
                .map(User::getId)
                .log();
    }

    @GetMapping
    public Flux<User> getAll() {
        log.info("Request to list users received.");

        return userRepository.findAll();
    }


    @DeleteMapping
    public Mono<Void> nuke() {
        log.info("Request to delete all users received.");

        return userRepository.deleteAll();
    }


}
