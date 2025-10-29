package space.personalshowcase.restaurant_review_platform.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import space.personalshowcase.restaurant_review_platform.domain.RestaurantCreateUpdateRequest;
import space.personalshowcase.restaurant_review_platform.domain.dtos.RestaurantCreateUpdateRequestDto;
import space.personalshowcase.restaurant_review_platform.domain.dtos.RestaurantDto;
import space.personalshowcase.restaurant_review_platform.domain.dtos.RestaurantSummaryDto;
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

    @GetMapping
    public Page<RestaurantSummaryDto> searchRestaurants(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Float minRating,
            @RequestParam(required = false) Float latitude,
            @RequestParam(required = false) Float longitude,
            @RequestParam(required = false) Float radius,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size
    ){
        Page<Restaurant> searchedRestaurants = restaurantService.searchRestaurants(
                q , minRating , latitude , longitude , radius , PageRequest.of(page - 1 , size)
        );

        return searchedRestaurants.map(restaurantMapper::toRestaurantSummaryDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getRestaurant(
            @PathVariable("id") String id
    ){
        return restaurantService.getRestaurant(id).map(restaurant -> ResponseEntity.ok(restaurantMapper.toRestaurantDto(restaurant)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public  ResponseEntity<RestaurantDto> updateRestaurants(@PathVariable("id") String id , @RequestBody @Valid RestaurantCreateUpdateRequestDto request){
        RestaurantCreateUpdateRequest convertedRequest = restaurantMapper.toRestaurantCreateUpdateRequest(request);
        Restaurant restaurant  =  restaurantService.updateRestaurants(id , convertedRequest);
        RestaurantDto updatedRestaurantDto = restaurantMapper.toRestaurantDto(restaurant);
        return  new ResponseEntity<>(updatedRestaurantDto , HttpStatus.OK);
    }

}
