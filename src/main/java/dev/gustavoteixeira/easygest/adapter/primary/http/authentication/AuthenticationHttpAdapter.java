package dev.gustavoteixeira.easygest.adapter.primary.http.authentication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
class AuthenticationHttpAdapter {
    private final JwtTokenProvider tokenProvider;
    private final ReactiveAuthenticationManager authenticationManager;

    @PostMapping("/login")
    Mono<ResponseEntity<Map<String, String>>> login(@RequestBody Mono<AuthenticationRequest> authRequest) {
        return authRequest
                .flatMap(login -> {
                    log.info("Request to login received from: {}.", login.getUsername());

                    return this.authenticationManager
                            .authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()))
                            .map(this.tokenProvider::createToken);
                }).map(jwt -> {
                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);
                    var tokenBody = Map.of("access_token", jwt);
                    return new ResponseEntity<>(tokenBody, httpHeaders, HttpStatus.OK);
                });
    }

}
