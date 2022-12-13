package dev.gustavoteixeira.easygest.adapter.primary.http.scheduling;

import dev.gustavoteixeira.easygest.model.scheduling.NewScheduling;
import dev.gustavoteixeira.easygest.model.scheduling.Scheduling;
import dev.gustavoteixeira.easygest.model.service.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Mapper
interface SchedulingHttpMapper {

    @Mapping(target = "status", ignore = true)
    NewScheduling toNewScheduling(NewSchedulingRequest newSchedulingRequest);

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "servicesId", source = "services", qualifiedByName = "servicesId")
    SchedulingResponse toSchedulingResponse(Scheduling scheduling);


    @Named("servicesId")
    default List<String> servicesToServicesId(List<Service> services) {
        return services.stream()
                .map(Service::getId)
                .collect(toList());
    }


}
