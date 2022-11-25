package dev.gustavoteixeira.easygest.adapter.secondary.db.rating;

import dev.gustavoteixeira.easygest.model.rating.NewRating;
import dev.gustavoteixeira.easygest.model.rating.Rating;
import dev.gustavoteixeira.easygest.model.rating.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
class RatingRepositoryImpl implements RatingRepository {

    private final RatingRepositoryMongoAdapter mongoAdapter;
    private final RatingMapper mapper;

    @Override
    public Mono<String> rate(Mono<NewRating> newRating) {
        return newRating.map(mapper::toRatingDocument)
                .flatMap(mongoAdapter::save)
                .map(RatingDocument::getId);
    }

    @Override
    public Flux<Rating> findAllRatesOfService(Mono<String> serviceId) {
        return mongoAdapter.findAllByServiceId(serviceId)
                .map(mapper::toRating);
    }

    @Override
    public Flux<Rating> findAllRatesOfUser(Mono<String> userId) {
        return mongoAdapter.findAllByUserId(userId)
                .map(mapper::toRating);
    }

    @Override
    public Mono<Void> delete(Mono<String> ratingId) {
        return mongoAdapter.findById(ratingId)
                .flatMap(mongoAdapter::delete)
                .then();
    }

    @Override
    public Flux<Rating> list() {
        return mongoAdapter.findAll()
                .map(mapper::toRating);
    }

}
