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

import static reactor.function.TupleUtils.function;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/services")
public class ServiceHttpAdapter {

    private final EasygestApplication easygestApplication;
    private final ServiceHttpMapper mapper;

    @PostMapping
    Mono<ResponseEntity<Void>> create(@RequestBody Mono<NewServiceRequest> newServiceRequest) {

        return newServiceRequest.map(mapper::toNewService)
                .flatMap(newService -> easygestApplication.create(Mono.just(newService)))
                .map(url -> ResponseEntity.created(URI.create(url)).build());
    }

    @GetMapping
    Flux<Service> list() {

        return easygestApplication.list();
    }

    @PutMapping("/{id}")
    Mono<ResponseEntity<Service>> update(@PathVariable String id, @RequestBody Mono<UpdateServiceRequest> newServiceRequest) {

        return newServiceRequest.zipWith(Mono.just(id))
                .map(function(mapper::toService))
                .flatMap(newService -> easygestApplication.update(Mono.just(newService)))
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        log.info("Deleting service {}", id);
        return  easygestApplication.delete(Mono.just(id))
                .map(ResponseEntity::ok);
    }

}
