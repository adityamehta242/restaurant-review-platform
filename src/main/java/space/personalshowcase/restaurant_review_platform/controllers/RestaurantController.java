package space.personalshowcase.restaurant_review_platform.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.personalshowcase.restaurant_review_platform.mappers.RestaurantMapper;
import space.personalshowcase.restaurant_review_platform.services.RestaurantService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;



}
