package space.personalshowcase.restaurant_review_platform.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeoPointDto {

    private  Double latitude;

    private  Double longitude;
}
