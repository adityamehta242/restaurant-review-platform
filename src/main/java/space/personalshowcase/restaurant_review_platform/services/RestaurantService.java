package space.personalshowcase.restaurant_review_platform.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import space.personalshowcase.restaurant_review_platform.domain.RestaurantCreateUpdateRequest;
import space.personalshowcase.restaurant_review_platform.domain.entities.Restaurant;

import java.util.Optional;

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

    Optional<Restaurant> getRestaurant(String id);

    Restaurant updateRestaurants(String id , RestaurantCreateUpdateRequest restaurantCreateUpdateRequest);

    void deleteRestaurant(String id);
}
