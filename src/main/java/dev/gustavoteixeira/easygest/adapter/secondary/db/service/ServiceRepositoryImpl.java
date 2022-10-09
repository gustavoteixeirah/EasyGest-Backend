package dev.gustavoteixeira.easygest.adapter.secondary.db.service;

import dev.gustavoteixeira.easygest.model.service.Service;
import dev.gustavoteixeira.easygest.model.service.ServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class ServiceRepositoryImpl implements ServiceRepository {

    private final ServiceRepositoryMongoAdapter mongoAdapter;
    private final ServiceMapper mapper;

    @Override
    public Service create(Service service) {
        return null;
    }

    @Override
    public Service update(Service service) {
        return null;
    }

    @Override
    public void delete(String serviceId) {

    }
}
