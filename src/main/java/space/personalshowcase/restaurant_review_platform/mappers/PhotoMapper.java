package space.personalshowcase.restaurant_review_platform.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import space.personalshowcase.restaurant_review_platform.domain.entities.Photo;
import space.personalshowcase.restaurant_review_platform.domain.dtos.PhotoDto;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhotoMapper {
    
    PhotoDto toDto(Photo photo);
}
