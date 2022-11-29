package dev.gustavoteixeira.easygest.adapter.secondary.db.scheduling;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SchedulingRepositoryMongoAdapter extends ReactiveCrudRepository<SchedulingDocument, String> {
}
