package dev.gustavoteixeira.easygest.adapter.secondary.db.service;

import dev.gustavoteixeira.easygest.model.service.Service;
import org.mapstruct.Mapper;

@Mapper
public interface ServiceMapper {

    Service toService(ServiceDocument serviceDocument);

    ServiceDocument toServiceDocument(Service service);

}
