package dev.gustavoteixeira.easygest.adapter.primary.http.user;

import dev.gustavoteixeira.easygest.adapter.primary.http.authentication.AuthenticationFacade;
import dev.gustavoteixeira.easygest.model.user.User;
import dev.gustavoteixeira.easygest.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

import static java.util.List.of;
import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserHttpAdapter {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationFacade authenticationFacade;

    @PostMapping
    @ResponseStatus(CREATED)
    public Mono<String> create(@RequestBody Mono<NewUserRequest> newUser) {
        log.info("Request to create a new user received.");

        return newUser.map(nur -> User.builder()
                        .fullName(nur.getFullName())
                        .username(nur.getUsername())
                        .password(passwordEncoder.encode(nur.getPassword()))
                        .email(nur.getEmail())
                        .roles(of("REGULAR_USER"))
                        .build())
                .flatMap(userRepository::insert)
                .map(User::getId)
                .log();
    }

    @PostMapping("/partners")
    @ResponseStatus(CREATED)
    public Mono<String> createPartner(@RequestBody Mono<NewUserRequest> newUser) {
        log.info("Request to create a new user received.");

        return newUser.map(nur -> User.builder()
                        .fullName(nur.getFullName())
                        .username(nur.getUsername())
                        .password(passwordEncoder.encode(nur.getPassword()))
                        .email(nur.getEmail())
                        .roles(of("PARTNER"))
                        .cnpj(nur.getCnpj())
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

    @GetMapping("/me")
    public Mono<User> getMyData() {
        log.info("Request to get data from the user making the request.");

        return authenticationFacade.getAuthentication()
                .map(Principal::getName)
                .flatMap(userRepository::findByUsername);
    }

    @DeleteMapping
    public Mono<Void> nuke() {
        log.info("Request to delete all users received.");

        return userRepository.deleteAll();
    }


}
