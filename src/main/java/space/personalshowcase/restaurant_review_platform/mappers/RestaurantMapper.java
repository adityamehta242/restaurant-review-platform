package space.personalshowcase.restaurant_review_platform.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import space.personalshowcase.restaurant_review_platform.domain.RestaurantCreateUpdateRequest;
import space.personalshowcase.restaurant_review_platform.domain.dtos.GeoPointDto;
import space.personalshowcase.restaurant_review_platform.domain.dtos.RestaurantCreateUpdateRequestDto;
import space.personalshowcase.restaurant_review_platform.domain.dtos.RestaurantDto;
import space.personalshowcase.restaurant_review_platform.domain.entities.Restaurant;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {

    RestaurantCreateUpdateRequest toRestaurantCreateUpdateRequest(RestaurantCreateUpdateRequestDto dto);

    RestaurantDto toRestaurant(Restaurant restaurant);

    @Mapping(target = "latitude",expression = "java(geoPoint.getLat())")
    @Mapping(target = "longitude",expression = "java(geoPoint.getLon())")
    GeoPointDto toGeoPoint(GeoPoint geoPoint);
}
