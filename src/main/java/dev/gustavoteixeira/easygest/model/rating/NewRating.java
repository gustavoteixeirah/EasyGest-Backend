package dev.gustavoteixeira.easygest.model.rating;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class NewRating {

    Stars stars;

    String description;

}
