package space.personalshowcase.restaurant_review_platform.services;

import space.personalshowcase.restaurant_review_platform.domain.ReviewCreateUpdateRequest;
import space.personalshowcase.restaurant_review_platform.domain.entities.Review;
import space.personalshowcase.restaurant_review_platform.domain.entities.User;

public interface ReviewService {
    Review createReview(User author, String restaurantId, ReviewCreateUpdateRequest review);
}
