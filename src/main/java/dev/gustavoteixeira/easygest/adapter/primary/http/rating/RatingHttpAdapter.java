package dev.gustavoteixeira.easygest.adapter.primary.http.rating;

import dev.gustavoteixeira.easygest.application.EasygestApplication;
import dev.gustavoteixeira.easygest.model.rating.Rating;
import dev.gustavoteixeira.easygest.model.service.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

import static reactor.core.publisher.Mono.just;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ratings")
public class RatingHttpAdapter {

    private final EasygestApplication easygestApplication;
    private final RatingHttpMapper mapper;


    @PostMapping
    Mono<ResponseEntity<Void>> create(@RequestBody Mono<NewRatingRequest> newRatingRequest) {
        log.info("Request to create a new rating.");

        return newRatingRequest.map(mapper::toNewRating)
                .flatMap(newRating -> easygestApplication.createNewRating(just(newRating)))
                .map(url -> ResponseEntity.created(URI.create(url)).build());
    }

    @GetMapping
    Flux<Rating> list() {
        log.info("Request to list all ratings.");

        return easygestApplication.listRatings();
    }

}
