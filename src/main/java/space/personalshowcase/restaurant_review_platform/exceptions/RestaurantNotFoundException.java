package space.personalshowcase.restaurant_review_platform.exceptions;

public class RestaurantNotFoundException extends RuntimeException {
  public RestaurantNotFoundException(String message) {
    super(message);
  }
}
