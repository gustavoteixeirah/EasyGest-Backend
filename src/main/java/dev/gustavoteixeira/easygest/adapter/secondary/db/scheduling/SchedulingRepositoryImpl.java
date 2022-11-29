package dev.gustavoteixeira.easygest.adapter.secondary.db.scheduling;

import dev.gustavoteixeira.easygest.model.scheduling.Scheduling;
import dev.gustavoteixeira.easygest.model.scheduling.SchedulingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
class SchedulingRepositoryImpl implements SchedulingRepository {

    private final SchedulingRepositoryMongoAdapter mongoAdapter;
    private final SchedulingMapper mapper;

    @Override
    public Mono<String> create(Mono<Scheduling> newScheduling) {
        return newScheduling.map(mapper::toSchedulingDocument)
                .flatMap(mongoAdapter::save)
                .map(SchedulingDocument::getId);
    }

    @Override
    public Flux<Scheduling> list() {
        return mongoAdapter.findAll()
                .map(mapper::toScheduling);
    }

    @Override
    public Mono<Void> confirm(Mono<String> schedulingId) {
        return schedulingId.flatMap(mongoAdapter::findById)
                .map(mapper::toScheduling)
                .doOnNext(Scheduling::confirm)
                .map(mapper::toSchedulingDocument)
                .flatMap(mongoAdapter::save)
                .then();
    }

    @Override
    public Mono<Void> finish(Mono<String> schedulingId) {
        return schedulingId.flatMap(mongoAdapter::findById)
                .map(mapper::toScheduling)
                .doOnNext(Scheduling::finish)
                .map(mapper::toSchedulingDocument)
                .flatMap(mongoAdapter::save)
                .then();
    }

    @Override
    public Mono<Void> cancel(Mono<String> schedulingId) {
        return schedulingId.flatMap(mongoAdapter::findById)
                .map(mapper::toScheduling)
                .doOnNext(Scheduling::cancel)
                .map(mapper::toSchedulingDocument)
                .flatMap(mongoAdapter::save)
                .then();
    }
}
