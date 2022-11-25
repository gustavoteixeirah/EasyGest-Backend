package dev.gustavoteixeira.easygest.adapter.secondary.db.rating;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
interface RatingRepositoryMongoAdapter extends ReactiveMongoRepository<RatingDocument, String> {

    Flux<RatingDocument> findAllByServiceId(Mono<String> serviceId);

    Flux<RatingDocument> findAllByUserId(Mono<String> userId);

}
