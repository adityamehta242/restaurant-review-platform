package space.personalshowcase.restaurant_review_platform.services.impl;

import org.springframework.stereotype.Service;
import space.personalshowcase.restaurant_review_platform.domain.GeoLocation;
import space.personalshowcase.restaurant_review_platform.domain.entities.Address;
import space.personalshowcase.restaurant_review_platform.services.GeoLocationService;

import java.util.Random;

@Service
public class RandomGeoLocationService implements GeoLocationService {

    private static double MIN_LATITUDE= 6.75f;
    private static double MAX_LATITUDE= 37.10f;
    private static double MIN_LONGITUDE= 68.12f;
    private static double MAX_LONGITUDE= 97.42f;

    @Override
    public GeoLocation getLocation(Address address) {
        Random random = new Random();

        double latitude = MIN_LATITUDE + random.nextDouble() * (MAX_LATITUDE - MIN_LATITUDE);
        double longitude = MIN_LONGITUDE + random.nextDouble() * (MAX_LONGITUDE - MIN_LONGITUDE);

        return GeoLocation.builder()
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}
