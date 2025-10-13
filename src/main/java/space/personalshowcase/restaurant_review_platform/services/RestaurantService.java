package space.personalshowcase.restaurant_review_platform.services;

import space.personalshowcase.restaurant_review_platform.domain.RestaurantCreateUpdateRequest;
import space.personalshowcase.restaurant_review_platform.domain.entities.Restaurant;

public interface RestaurantService {
    Restaurant CreateRestaurant(RestaurantCreateUpdateRequest restaurantCreateUpdateRequest);

}
