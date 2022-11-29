package dev.gustavoteixeira.easygest.adapter.primary.http.service;


import dev.gustavoteixeira.easygest.application.EasygestApplication;
import dev.gustavoteixeira.easygest.model.service.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

import static reactor.core.publisher.Mono.just;
import static reactor.function.TupleUtils.function;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/services")
class ServiceHttpAdapter {

    private final EasygestApplication easygestApplication;
    private final ServiceHttpMapper mapper;

    @PostMapping
    Mono<ResponseEntity<Void>> create(@RequestBody Mono<NewServiceRequest> newServiceRequest) {
        log.info("Request to create a new service received.");

        return newServiceRequest.map(mapper::toNewService)
                .flatMap(newService -> easygestApplication.createNewService(just(newService)))
                .map(url -> ResponseEntity.created(URI.create(url)).build());
    }

    @GetMapping
    Flux<Service> list() {
        log.info("Request to list all services received.");

        return easygestApplication.listServices();
    }

    @PutMapping("/{id}")
    Mono<ResponseEntity<Service>> update(@PathVariable String id, @RequestBody Mono<UpdateServiceRequest> newServiceRequest) {
        log.info("Request to update a existing service received.");

        return newServiceRequest.zipWith(just(id))
                .map(function(mapper::toService))
                .flatMap(newService -> easygestApplication.update(just(newService)))
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        log.info("Request to delete a existing service received.");

        return easygestApplication.delete(just(id))
                .map(ResponseEntity::ok);
    }

}
