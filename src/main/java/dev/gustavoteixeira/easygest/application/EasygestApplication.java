package dev.gustavoteixeira.easygest.application;


import dev.gustavoteixeira.easygest.model.service.NewService;
import dev.gustavoteixeira.easygest.model.service.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EasygestApplication {

    Mono<String> create(Mono<NewService> service);

    Flux<Service> list();


    Mono<Service> update(Mono<Service> service);

    Mono<Void> delete(Mono<String> serviceId);


}
