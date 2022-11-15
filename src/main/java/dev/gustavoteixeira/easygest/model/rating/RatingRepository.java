package dev.gustavoteixeira.easygest.model.rating;

public interface RatingRepository {

    void rate(String serviceId, NewRating newRating);

    void delete(String ratingId);

}
