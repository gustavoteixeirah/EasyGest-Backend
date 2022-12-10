package dev.gustavoteixeira.easygest.adapter.primary.http.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
class AuthenticationFacadeImpl implements AuthenticationFacade {

    @Override
    public Mono<Authentication> getAuthentication() {

        return Mono.just(ReactiveSecurityContextHolder.getContext())
                .flatMap(authentication -> authentication.map(SecurityContext::getAuthentication));
    }

}
