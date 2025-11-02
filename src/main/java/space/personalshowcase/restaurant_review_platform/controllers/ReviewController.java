package space.personalshowcase.restaurant_review_platform.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import space.personalshowcase.restaurant_review_platform.domain.ReviewCreateUpdateRequest;
import space.personalshowcase.restaurant_review_platform.domain.dtos.ReviewCreateUpdateRequestDto;
import space.personalshowcase.restaurant_review_platform.domain.dtos.ReviewDto;
import space.personalshowcase.restaurant_review_platform.domain.entities.Review;
import space.personalshowcase.restaurant_review_platform.domain.entities.User;
import space.personalshowcase.restaurant_review_platform.mappers.ReviewMapper;
import space.personalshowcase.restaurant_review_platform.services.ReviewService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants/{restaurantId}/reviews")
public class ReviewController {

    private final ReviewMapper reviewMapper;
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDto> createReview(
            @PathVariable("restaurantId") String restaurantId ,
            @Valid  @RequestBody ReviewCreateUpdateRequestDto review,
            @AuthenticationPrincipal Jwt jwt){

        ReviewCreateUpdateRequest dto = reviewMapper.toReviewCreateUpdateRequest(review);
        User user = jwtToUser(jwt);
        Review saveReview = reviewService.createReview(user, restaurantId , dto);

        return new ResponseEntity<>(reviewMapper.toReviewDto(saveReview) , HttpStatus.OK);
    }

    private User jwtToUser(Jwt jwt){
        return User.builder()
                .id(jwt.getSubject())
                .username(jwt.getClaimAsString("preferred_username"))
                .givenName(jwt.getClaimAsString("given_name"))
                .familyName(jwt.getClaimAsString("family_name"))
                .build();
    }
}
