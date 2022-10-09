package dev.gustavoteixeira.easygest.application;


import dev.gustavoteixeira.easygest.model.service.NewService;
import reactor.core.publisher.Mono;

public interface EasygestApplication {

    Mono<String> createService(Mono<NewService> newService);


}
