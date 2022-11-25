package dev.gustavoteixeira.easygest.application;

import dev.gustavoteixeira.easygest.model.rating.NewRating;
import dev.gustavoteixeira.easygest.model.rating.Rating;
import dev.gustavoteixeira.easygest.model.rating.RatingRepository;
import dev.gustavoteixeira.easygest.model.service.NewService;
import dev.gustavoteixeira.easygest.model.service.Service;
import dev.gustavoteixeira.easygest.model.service.ServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class EasygestApplicationImpl implements EasygestApplication {

    private final ServiceRepository serviceRepository;
    private final RatingRepository ratingRepository;

    @Override
    public Mono<String> createNewService(Mono<NewService> service) {
        return serviceRepository.create(service);
    }

    @Override
    public Mono<String> createNewRating(Mono<NewRating> newRating) {
        return ratingRepository.rate(newRating);
    }

    @Override
    public Flux<Service> list() {
        return serviceRepository.list();
    }

    @Override
    public Mono<Service> update(Mono<Service> service) {
        return serviceRepository.update(service);
    }

    @Override
    public Mono<Void> delete(Mono<String> serviceId) {
        return serviceRepository.delete(serviceId)
                .then();
    }

    @Override
    public Flux<Rating> listServiceRatings(String id) {
        return ratingRepository.findAllRatesOfService(Mono.just(id));
    }

    @Override
    public Flux<Rating> listRatings() {
        return ratingRepository.list();
    }

}
