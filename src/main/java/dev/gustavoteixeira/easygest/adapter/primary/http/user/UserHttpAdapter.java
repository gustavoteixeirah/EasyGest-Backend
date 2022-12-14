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
import static org.springframework.http.HttpStatus.OK;

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

    @PutMapping("/partners/{id}")
    @ResponseStatus(OK)
    public Mono<String> createPartner(@PathVariable String id, @RequestBody Mono<NewUserRequest> newUser) {
        log.info("Request to update a partner received.");
        return userRepository.findById(id).zipWith(newUser).map((tuple) -> tuple.getT1().toBuilder()
                .fullName(tuple.getT2().getFullName())
                .username(tuple.getT2().getUsername())
                .email(tuple.getT2().getEmail())
                .cnpj(tuple.getT2().getCnpj())
                .build())
                .flatMap(userRepository::save)
                .map(User::getId);
    }

    @GetMapping
    public Flux<User> getAll() {
        log.info("Request to list users received.");

        return userRepository.findAll();
    }

    @GetMapping("/partners")
    public Flux<User> getAllPartners() {
        log.info("Request to list partners received.");

        return userRepository.findAllByRolesContaining("PARTNER");
    }

    @GetMapping("/regular-users")
    public Flux<User> getAllRegularUsers() {
        log.info("Request to list partners received.");

        return userRepository.findAllByRolesContaining("REGULAR_USER");
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

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable String id) {
        log.info("Request to delete all users received.");

        return userRepository.deleteById(id);
    }

}
