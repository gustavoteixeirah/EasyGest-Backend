package dev.gustavoteixeira.easygest.adapter.primary.http.scheduling;

import dev.gustavoteixeira.easygest.model.scheduling.NewScheduling;
import dev.gustavoteixeira.easygest.model.scheduling.Scheduling;
import dev.gustavoteixeira.easygest.model.service.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.RoundingMode;
import java.util.List;

import static java.math.RoundingMode.HALF_EVEN;
import static java.util.stream.Collectors.toList;

@Mapper
interface SchedulingHttpMapper {

    @Mapping(target = "status", ignore = true)
    NewScheduling toNewScheduling(NewSchedulingRequest newSchedulingRequest);

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "servicesDescription", source = "services", qualifiedByName = "servicesDescription")
    @Mapping(target = "price", source = "scheduling", qualifiedByName = "getPriceAsString")
    SchedulingResponse toSchedulingResponse(Scheduling scheduling);

    @Named("servicesDescription")
    default List<String> servicesToServicesDescription(List<Service> services) {
        return services.stream()
                .map(Service::getDescription)
                .collect(toList());
    }

    @Named("getPriceAsString")
    default String getPriceAsString(Scheduling scheduling) {

        return scheduling
                .getPrice()
                .setScale(2, HALF_EVEN)
                .toString();
    }


}
