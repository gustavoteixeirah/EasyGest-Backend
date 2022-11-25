package dev.gustavoteixeira.easygest.model.rating;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RatingRepository {

    Mono<String> rate(Mono<NewRating> newRating);

    Flux<Rating> findAllRatesOfService(Mono<String> serviceId);

    Flux<Rating> findAllRatesOfUser(Mono<String> userId);

    Mono<Void> delete(Mono<String> ratingId);

    Flux<Rating> list();
}
