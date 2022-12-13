package dev.gustavoteixeira.easygest.adapter.secondary.db.scheduling;

import dev.gustavoteixeira.easygest.model.scheduling.Scheduling;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
interface SchedulingMapper {

    @Mapping(target = "customer.password", ignore = true)
    Scheduling toScheduling(SchedulingDocument schedulingDocument);

    SchedulingDocument toSchedulingDocument(Scheduling scheduling);

}
