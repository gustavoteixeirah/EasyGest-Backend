package dev.gustavoteixeira.easygest.model.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ServiceRepository {

    Mono<String> create(Mono<NewService> newService);

    Flux<Service> list();

    Mono<Service> update(Mono<Service> service);

    Mono<Void> delete(Mono<String> serviceId);

}
