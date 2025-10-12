package space.personalshowcase.restaurant_review_platform.services;

import space.personalshowcase.restaurant_review_platform.domain.GeoLocation;
import space.personalshowcase.restaurant_review_platform.domain.entities.Address;

public interface GeoLocationService {
    GeoLocation getLocation(Address address);
}
