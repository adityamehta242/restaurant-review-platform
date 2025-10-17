package space.personalshowcase.restaurant_review_platform.manual;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.multipart.MultipartFile;
import space.personalshowcase.restaurant_review_platform.domain.RestaurantCreateUpdateRequest;
import space.personalshowcase.restaurant_review_platform.domain.entities.Photo;
import space.personalshowcase.restaurant_review_platform.services.PhotoService;
import space.personalshowcase.restaurant_review_platform.services.RestaurantService;
import space.personalshowcase.restaurant_review_platform.services.impl.RandomGeoLocationService;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class RestaurantDataLoaderTest {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RandomGeoLocationService geoLocationService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    @Rollback(false)
    public void createSampleRestaurant() throws Exception{

        List<RestaurantCreateUpdateRequest> restaurants = createRestaurantData();

        restaurants.forEach(restaurant -> {
            String fileName = restaurant.getPhotoIds().getFirst();

            Resource resource = resourceLoader.getResource("classpath:testdata/" + fileName);
            MultipartFile multipartFile = null;

            try {
                multipartFile = new MockMultipartFile(
                        "file",
                        fileName,
                        MediaType.IMAGE_PNG_VALUE,
                        resource.getInputStream()
                );
            } catch (IOException ex){
                throw new RuntimeException(ex);
            }

            Photo uploadPhoto = photoService.uploadPhoto(multipartFile);
            restaurant.setPhotoIds(List.of(uploadPhoto.getUrl()));
            restaurantService.CreateRestaurant(restaurant);

            System.out.println("Created Restaurant: " + restaurant.getName());
        });
    }

    List<RestaurantCreateUpdateRequest> createRestaurantData(){
        return null;
    }



}
