package dev.gustavoteixeira.easygest.model.service;

public interface ServiceRepository {

    Service create(Service service);

    Service update(Service service);

    void delete(String serviceId);

}
