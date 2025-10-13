package space.personalshowcase.restaurant_review_platform.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import space.personalshowcase.restaurant_review_platform.domain.entities.Address;
import space.personalshowcase.restaurant_review_platform.domain.entities.OperatingHours;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RestaurantCreateUpdateRequest {
    private String name;
    private String cuisineType;
    private  String contactInformation;
    private Address address;
    private OperatingHours operatingHours;
    private Float averageRating;
    private List<String> photoIds;
}
