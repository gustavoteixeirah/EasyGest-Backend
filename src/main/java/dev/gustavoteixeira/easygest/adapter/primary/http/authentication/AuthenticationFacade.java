package dev.gustavoteixeira.easygest.adapter.primary.http.authentication;

import org.springframework.security.core.Authentication;
import reactor.core.publisher.Mono;

public interface AuthenticationFacade {

    Mono<Authentication> getAuthentication();

}
