package dev.gustavoteixeira.easygest.model.rating;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class Rating {

    String id;

    String schedulingId;

    Stars stars;

    String description;

    LocalDateTime dateTime;

}
