package dev.gustavoteixeira.easygest.adapter.secondary.db.service;

import dev.gustavoteixeira.easygest.model.service.NewService;
import dev.gustavoteixeira.easygest.model.service.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ServiceMapper {

    Service toService(ServiceDocument serviceDocument);

    @Mapping(target = "id", ignore = true)
    ServiceDocument toServiceDocument(NewService newDocument);

    ServiceDocument toServiceDocument(Service service);

}
