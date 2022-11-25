package dev.gustavoteixeira.easygest.adapter.primary.http.rating;

import dev.gustavoteixeira.easygest.model.rating.NewRating;
import org.mapstruct.Mapper;

@Mapper
interface RatingHttpMapper {

    NewRating toNewRating(NewRatingRequest newRatingRequest);

}
