package space.personalshowcase.restaurant_review_platform.services;

import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import space.personalshowcase.restaurant_review_platform.domain.entities.Photo;

public interface PhotoService {

    Photo uploadPhoto(MultipartFile file);
    Optional<Resource> getPhotoAsResources(String id);
}
