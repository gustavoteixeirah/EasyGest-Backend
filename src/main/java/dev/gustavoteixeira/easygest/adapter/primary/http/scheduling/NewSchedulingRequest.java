package dev.gustavoteixeira.easygest.adapter.primary.http.scheduling;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class NewSchedulingRequest {

    String customerId;

    LocalDateTime dateTime;

    List<String> servicesId;

}
