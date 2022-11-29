package dev.gustavoteixeira.easygest.adapter.secondary.db.service;

import dev.gustavoteixeira.easygest.model.service.NewService;
import dev.gustavoteixeira.easygest.model.service.Service;
import dev.gustavoteixeira.easygest.model.service.ServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
class ServiceRepositoryImpl implements ServiceRepository {

    private final ServiceRepositoryMongoAdapter mongoAdapter;
    private final ServiceMapper mapper;


    @Override
    public Mono<String> create(Mono<NewService> newService) {
        return newService.map(mapper::toServiceDocument)
                .flatMap(mongoAdapter::save)
                .map(ServiceDocument::getId);
    }

    @Override
    public Flux<Service> list() {
        return mongoAdapter.findAll()
                .map(mapper::toService);
    }

    @Override
    public Mono<Service> update(Mono<Service> service) {
        return service.map(mapper::toServiceDocument)
                .flatMap(mongoAdapter::save)
                .map(mapper::toService);
    }

    @Override
    public Mono<Service> findById(String serviceId) {
        return Mono.just(serviceId)
                .flatMap(mongoAdapter::findById)
                .map(mapper::toService);
    }

    @Override
    public Mono<Void> delete(Mono<String> serviceId) {
        return mongoAdapter.findById(serviceId)
                .flatMap(mongoAdapter::delete)
                .then();
    }
}
