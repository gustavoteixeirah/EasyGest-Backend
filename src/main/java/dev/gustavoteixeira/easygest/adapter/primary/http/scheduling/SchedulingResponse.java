package dev.gustavoteixeira.easygest.adapter.primary.http.scheduling;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class SchedulingResponse {

    String id;

    String customerId;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", shape = STRING)
    LocalDateTime dateTime;

    List<String> servicesDescription;

    int duration;

    String price;

}
