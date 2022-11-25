package dev.gustavoteixeira.easygest.application;


import dev.gustavoteixeira.easygest.model.rating.NewRating;
import dev.gustavoteixeira.easygest.model.rating.Rating;
import dev.gustavoteixeira.easygest.model.service.NewService;
import dev.gustavoteixeira.easygest.model.service.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EasygestApplication {

    Mono<String> createNewService(Mono<NewService> service);

    Mono<String> createNewRating(Mono<NewRating> rating);

    Flux<Service> list();


    Mono<Service> update(Mono<Service> service);

    Mono<Void> delete(Mono<String> serviceId);

    Flux<Rating> listServiceRatings(String id);

    Flux<Rating> listRatings();
}
