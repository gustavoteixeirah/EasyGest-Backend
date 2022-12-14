package dev.gustavoteixeira.easygest.adapter.primary.http.authentication;

import dev.gustavoteixeira.easygest.model.user.Roles;
import dev.gustavoteixeira.easygest.model.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import reactor.core.publisher.Mono;

import java.util.List;

import static dev.gustavoteixeira.easygest.model.user.Roles.*;
import static org.springframework.http.HttpMethod.*;

@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http,
                                                JwtTokenProvider tokenProvider,
                                                ReactiveAuthenticationManager reactiveAuthenticationManager) {
        final String USERS_PATH = "/users";
        final String USERS_ME_PATH = "/users/me";
        final String USERS_PARTNERS_PATH = "/users/partners";
        final String USERS_PARTNERS_PATH_FULL_PATH = "/users/partners/**";
        final String USERS_REGULAR_USER_PATH = "/users/regular-users";
        final String SERVICES_PATH = "/services";
        final String SERVICES_FULL_PATH = "/services/**";

        return http
                .cors()
                .configurationSource(corsConfig -> corsConfig())
                .and()
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .authenticationManager(reactiveAuthenticationManager)
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .authorizeExchange(it -> it
                        .pathMatchers(POST, "/login").permitAll()
                        .pathMatchers(POST, USERS_PATH).permitAll()
                        .pathMatchers(GET, USERS_ME_PATH).permitAll()
                        .pathMatchers(GET, USERS_PATH).hasAnyAuthority(REGULAR_USER.name(), PARTNER.name(), SYSTEM_ADMIN.name())
                        .pathMatchers(SERVICES_PATH).hasAnyAuthority(REGULAR_USER.name(), PARTNER.name(), SYSTEM_ADMIN.name())
                        .pathMatchers(SERVICES_FULL_PATH).hasAnyAuthority(REGULAR_USER.name(), PARTNER.name(), SYSTEM_ADMIN.name())
                        .pathMatchers("/actuator/**").hasAuthority(SYSTEM_ADMIN.name())
                        .pathMatchers(USERS_PARTNERS_PATH_FULL_PATH).hasAuthority(SYSTEM_ADMIN.name())
                        .pathMatchers(POST, USERS_PARTNERS_PATH).hasAuthority(SYSTEM_ADMIN.name())
                        .pathMatchers(GET, USERS_PARTNERS_PATH).hasAuthority(SYSTEM_ADMIN.name())
                        .pathMatchers(GET, USERS_PARTNERS_PATH).hasAuthority(SYSTEM_ADMIN.name())
                        .pathMatchers(GET, USERS_REGULAR_USER_PATH).hasAuthority(SYSTEM_ADMIN.name())
                        .pathMatchers(DELETE, USERS_PATH).hasAuthority(SYSTEM_ADMIN.name())
                        .pathMatchers("/users/{user}/**").access(this::currentUserMatchesPath)
                        .anyExchange().permitAll()
                )
                .addFilterAt(new JwtTokenAuthenticationFilter(tokenProvider), SecurityWebFiltersOrder.HTTP_BASIC)
                .build();


    }

    private static CorsConfiguration corsConfig() {
        var corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        return corsConfiguration;
    }

    private Mono<AuthorizationDecision> currentUserMatchesPath(Mono<Authentication> authentication,
                                                               AuthorizationContext context) {

        return authentication
                .map(a -> context.getVariables().get("user").equals(a.getName()))
                .map(AuthorizationDecision::new);

    }

    @Bean
    public ReactiveUserDetailsService userDetailsService(UserRepository users) {

        return username -> users.findByUsername(username)
                .map(u -> User
                        .withUsername(u.getUsername()).password(u.getPassword())
                        .authorities(u.getRoles().toArray(new String[0]))
                        .accountExpired(!u.isActive())
                        .credentialsExpired(!u.isActive())
                        .disabled(!u.isActive())
                        .accountLocked(!u.isActive())
                        .build()
                );
    }

    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService,
                                                                       PasswordEncoder passwordEncoder) {
        var authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
        authenticationManager.setPasswordEncoder(passwordEncoder);
        return authenticationManager;
    }

}
