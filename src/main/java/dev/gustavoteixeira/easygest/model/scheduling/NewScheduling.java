package dev.gustavoteixeira.easygest.model.scheduling;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

import static dev.gustavoteixeira.easygest.model.scheduling.SchedulingState.REQUESTED;
import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class NewScheduling {

    String customerId;

    LocalDateTime dateTime;

    List<String> servicesId;

    @Builder.Default()
    SchedulingState status = REQUESTED;

}
