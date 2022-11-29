package dev.gustavoteixeira.easygest.model.scheduling;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SchedulingRepository {

    Mono<String> create(Mono<Scheduling> newScheduling);

    Flux<Scheduling> list();

    Mono<Void> confirm(Mono<String> schedulingId);

    Mono<Void> finish(Mono<String> schedulingId);

    Mono<Void> cancel(Mono<String> schedulingId);

}
