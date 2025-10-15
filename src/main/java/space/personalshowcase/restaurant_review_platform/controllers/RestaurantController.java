package space.personalshowcase.restaurant_review_platform.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.personalshowcase.restaurant_review_platform.domain.RestaurantCreateUpdateRequest;
import space.personalshowcase.restaurant_review_platform.domain.dtos.RestaurantCreateUpdateRequestDto;
import space.personalshowcase.restaurant_review_platform.domain.dtos.RestaurantDto;
import space.personalshowcase.restaurant_review_platform.domain.entities.Restaurant;
import space.personalshowcase.restaurant_review_platform.mappers.RestaurantMapper;
import space.personalshowcase.restaurant_review_platform.services.RestaurantService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    public ResponseEntity<RestaurantDto> createRestaurant(
           @RequestBody @Valid RestaurantCreateUpdateRequestDto request)
    {
        RestaurantCreateUpdateRequest restaurantCreateUpdateRequest =
                restaurantMapper.toRestaurantCreateUpdateRequest(request);

        Restaurant restaurant =  restaurantService.CreateRestaurant(restaurantCreateUpdateRequest);

        RestaurantDto createRestaurantDto = restaurantMapper.toRestaurantDto(restaurant);
        return ResponseEntity.ok(createRestaurantDto);
    }

}
