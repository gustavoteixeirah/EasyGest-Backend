package dev.gustavoteixeira.easygest.adapter.secondary.db.scheduling;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
interface SchedulingRepositoryMongoAdapter extends ReactiveCrudRepository<SchedulingDocument, String> {

    Flux<SchedulingDocument> findAllByCustomer_Username(String customerUsername);

}
