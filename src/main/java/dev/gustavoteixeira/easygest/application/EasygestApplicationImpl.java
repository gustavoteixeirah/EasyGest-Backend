package dev.gustavoteixeira.easygest.application;

import dev.gustavoteixeira.easygest.model.service.NewService;
import dev.gustavoteixeira.easygest.model.service.ServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class EasygestApplicationImpl implements EasygestApplication {

    private final ServiceRepository serviceRepository;

    @Override
    public Mono<String> createService(Mono<NewService> newService) {
        return null;
    }
}
