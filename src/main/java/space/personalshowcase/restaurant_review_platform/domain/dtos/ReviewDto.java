package space.personalshowcase.restaurant_review_platform.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import space.personalshowcase.restaurant_review_platform.domain.entities.Photo;
import space.personalshowcase.restaurant_review_platform.domain.entities.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {
    private String id;

    private String content;

    private Integer rating;

    private LocalDateTime datePosted;

    private LocalDateTime lastUpdated;

    private List<PhotoDto> photos = new ArrayList<>();

    private UserDto writtenBy;
}
