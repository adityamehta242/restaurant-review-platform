package space.personalshowcase.restaurant_review_platform.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import space.personalshowcase.restaurant_review_platform.domain.entities.Restaurant;

@Repository
public interface RestaurantRepository extends ElasticsearchRepository<Restaurant , String> {
    //TODO: custom query
}
