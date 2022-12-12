package dev.gustavoteixeira.easygest.adapter.primary.http.scheduling;

import dev.gustavoteixeira.easygest.application.EasygestApplication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.http.ResponseEntity.created;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/schedulings")
class SchedulingHttpAdapter {
    private final EasygestApplication easygestApplication;
    private final SchedulingHttpMapper mapper;

    @PostMapping
    Mono<ResponseEntity<Void>> create(@RequestBody Mono<NewSchedulingRequest> newSchedulingRequest) {
        log.info("Request to create a new service received.");

        return newSchedulingRequest.map(mapper::toNewScheduling)
                .flatMap(easygestApplication::createScheduling)
                .map(url -> created(URI.create(url)).build());
    }
}
