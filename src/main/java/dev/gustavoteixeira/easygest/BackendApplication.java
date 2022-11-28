package dev.gustavoteixeira.easygest;

import dev.gustavoteixeira.easygest.model.user.User;
import dev.gustavoteixeira.easygest.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.reactive.config.EnableWebFlux;

import javax.annotation.PostConstruct;

import static java.util.List.of;
import static reactor.core.scheduler.Schedulers.boundedElastic;

@EnableWebFlux
@SpringBootApplication
@RequiredArgsConstructor
public class BackendApplication {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }


    @PostConstruct
    private void postConstruct() {
        User systemAdmin = User.builder()
                .fullName("Gustavo Teixeira")
                .username("contact@gustavoteixeira.dev")
                .password(passwordEncoder.encode("kode@123"))
                .email("contact@gustavoteixeira.dev")
                .roles(of("SYSTEM_ADMIN"))
                .build();

        userRepository.insert(systemAdmin)
                .subscribeOn(boundedElastic())
                .subscribe();
    }

}
