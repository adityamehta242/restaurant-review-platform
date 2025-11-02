package space.personalshowcase.restaurant_review_platform.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import space.personalshowcase.restaurant_review_platform.domain.ReviewCreateUpdateRequest;
import space.personalshowcase.restaurant_review_platform.domain.dtos.ReviewCreateUpdateRequestDto;
import space.personalshowcase.restaurant_review_platform.domain.dtos.ReviewDto;
import space.personalshowcase.restaurant_review_platform.domain.entities.Review;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {
    ReviewCreateUpdateRequest toReviewCreateUpdateRequest(ReviewCreateUpdateRequestDto dto);

    ReviewDto toReviewDto(Review review);
}
