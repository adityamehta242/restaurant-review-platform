package space.personalshowcase.restaurant_review_platform.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import space.personalshowcase.restaurant_review_platform.domain.RestaurantCreateUpdateRequest;
import space.personalshowcase.restaurant_review_platform.domain.entities.Restaurant;

public interface RestaurantService {
    Restaurant CreateRestaurant(RestaurantCreateUpdateRequest restaurantCreateUpdateRequest);

    Page<Restaurant> searchRestaurants(
            String query,
            Float minRating,
            Float latitude,
            Float longitude,
            Float radius,
            Pageable pageable
    );
}
