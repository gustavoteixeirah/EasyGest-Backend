package dev.gustavoteixeira.easygest.adapter.secondary.db.rating;

import dev.gustavoteixeira.easygest.model.rating.NewRating;
import dev.gustavoteixeira.easygest.model.rating.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
interface RatingMapper {

    Rating toRating(RatingDocument ratingDocument);

    @Mapping(target = "id", ignore = true)
    RatingDocument toRatingDocument(NewRating rating);

}
