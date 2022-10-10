package dev.gustavoteixeira.easygest.application;

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
public class EasygestApplicationImpl implements EasygestApplication {

    private final ServiceRepository serviceRepository;

    @Override
    public Mono<String> create(Mono<NewService> service) {
        return serviceRepository.create(service);
    }

    @Override
    public Flux<Service> list() {
        return serviceRepository.list();
    }

    @Override
    public Mono<Service> update(Mono<Service> service) {
        return serviceRepository.update(service);
    }

    @Override
    public Mono<Void> delete(Mono<String> serviceId) {
        return serviceRepository.delete(serviceId)
                .then();
    }

}
