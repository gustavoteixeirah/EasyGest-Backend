package dev.gustavoteixeira.easygest.adapter.secondary.db.rating;

import dev.gustavoteixeira.easygest.model.rating.Stars;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
@Document("Ratings")
class RatingDocument {

    @Id
    String id;

    Stars stars;

    String serviceId;

    String userId;

    String description;

}
