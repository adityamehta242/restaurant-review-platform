package space.personalshowcase.restaurant_review_platform.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;
import space.personalshowcase.restaurant_review_platform.domain.GeoLocation;
import space.personalshowcase.restaurant_review_platform.domain.RestaurantCreateUpdateRequest;
import space.personalshowcase.restaurant_review_platform.domain.entities.Address;
import space.personalshowcase.restaurant_review_platform.domain.entities.Photo;
import space.personalshowcase.restaurant_review_platform.domain.entities.Restaurant;
import space.personalshowcase.restaurant_review_platform.repositories.RestaurantRepository;
import space.personalshowcase.restaurant_review_platform.services.GeoLocationService;
import space.personalshowcase.restaurant_review_platform.services.RestaurantService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final GeoLocationService geoLocationService;

    @Override
    public Restaurant CreateRestaurant(RestaurantCreateUpdateRequest restaurantCreateUpdateRequest) {
        Address address = restaurantCreateUpdateRequest.getAddress();
        GeoLocation geoLocation = geoLocationService.getLocation(address);
        GeoPoint geoPoint= new GeoPoint(geoLocation.getLatitude() , geoLocation.getLongitude());


        List<String> photoIds = restaurantCreateUpdateRequest.getPhotoIds();
        List<Photo> photos = photoIds.stream().map(photoUrl -> Photo.builder()
                .url(photoUrl)
                .uploadDate(LocalDateTime.now())
                .build()).toList();

        Restaurant restaurant = Restaurant.builder()
                .name(restaurantCreateUpdateRequest.getName())
                .cuisineType(restaurantCreateUpdateRequest.getCuisineType())
                .contactInformation(restaurantCreateUpdateRequest.getContactInformation())
                .address(restaurantCreateUpdateRequest.getAddress())
                .geoLocation(geoPoint)
                .operatingHours(restaurantCreateUpdateRequest.getOperatingHours())
                .averageRating(0f)
                .photos(photos)
                .build();

        return  restaurantRepository.save(restaurant);
    }

    @Override
    public Page<Restaurant> searchRestaurants(
            String query,
            Float minRating,
            Float latitude,
            Float longitude,
            Float radius, Pageable pageable ) {

            if(null != minRating && (null == query || query.isEmpty()))
            {
                return restaurantRepository.findByAverageRatingGreaterThanEqual(minRating ,pageable);
            }

            Float searchMinRating = null == minRating ? 0f : minRating;

            if (query !=null && !query.trim().isEmpty())
            {
                return  restaurantRepository.findByQueryAndMinRating(query , searchMinRating , pageable);
            }

            if (null != latitude && null != longitude && null != radius)
            {
                return  restaurantRepository.findByLocationNear(latitude , longitude , radius , pageable);
            }

            return restaurantRepository.findAll(pageable);
    }

    @Override
    public Optional<Restaurant> getRestaurant(String id) {
        return restaurantRepository.findById(id);
    }
}
