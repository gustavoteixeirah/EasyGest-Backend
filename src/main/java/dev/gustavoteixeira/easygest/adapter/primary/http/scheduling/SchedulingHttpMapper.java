package dev.gustavoteixeira.easygest.adapter.primary.http.scheduling;

import dev.gustavoteixeira.easygest.model.scheduling.NewScheduling;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
interface SchedulingHttpMapper {

    @Mapping(target = "status", ignore = true)
    NewScheduling toNewScheduling(NewSchedulingRequest newSchedulingRequest);

}
