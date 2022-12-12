package dev.gustavoteixeira.easygest.adapter.secondary.db.scheduling;

import dev.gustavoteixeira.easygest.model.scheduling.SchedulingState;
import dev.gustavoteixeira.easygest.model.service.Service;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
@Document("Schedulings")
class SchedulingDocument {

    @Id
    String id;

    LocalDateTime dateTime;

    List<Service> services;

    SchedulingState status;

    UserSchedulingDocument customer;

}
