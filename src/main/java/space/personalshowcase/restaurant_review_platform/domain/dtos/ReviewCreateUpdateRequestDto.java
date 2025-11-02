package space.personalshowcase.restaurant_review_platform.domain.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewCreateUpdateRequestDto {

    @NotBlank(message = "Review Content is Required")
    private String content;

    @NotNull(message = "Rating is Required")
    @Min(value = 1, message = "Rating must in between 1 and 5")
    @Max(value = 5, message = "Rating must in between 1 and 5")
    private Integer rating;


    private List<String> photoIds = new ArrayList<>();
}
