package dev.gustavoteixeira.easygest.adapter.primary.http.service;

import dev.gustavoteixeira.easygest.model.service.NewService;
import dev.gustavoteixeira.easygest.model.service.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
interface ServiceHttpMapper {

    NewService toNewService(NewServiceRequest newServiceRequest);

    @Mapping(target = "id", source = "id")
    Service toService(UpdateServiceRequest updateServiceRequest, String id);

}
