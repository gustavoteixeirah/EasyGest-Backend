package dev.gustavoteixeira.easygest.adapter.primary.http.user;

import dev.gustavoteixeira.easygest.model.user.User;
import dev.gustavoteixeira.easygest.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @PostMapping
    public Mono<String> current(@RequestBody Mono<NewUserRequest> newUser) {
        return newUser.map(nur -> User.builder()
                        .username(nur.getUsername())
                        .password(passwordEncoder.encode(nur.getPassword()))
                        .email(nur.getEmail()).build())
                .flatMap(userRepository::insert)
                .map(User::getId);
    }

    @GetMapping
    public Flux<User> getAll() {
        return userRepository.findAll();
    }


    @DeleteMapping
    public Mono<Void> nuke() {
        return userRepository.deleteAll();
    }


}
