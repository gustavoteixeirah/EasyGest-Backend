package dev.gustavoteixeira.easygest.adapter.primary.http.rating;

import dev.gustavoteixeira.easygest.model.rating.Stars;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class NewRatingRequest {

    Stars stars;

    String serviceId;

    String userId;

    String description;

}
