package space.personalshowcase.restaurant_review_platform.services.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import space.personalshowcase.restaurant_review_platform.domain.entities.Photo;
import space.personalshowcase.restaurant_review_platform.services.PhotoService;
import space.personalshowcase.restaurant_review_platform.services.StorageService;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService{

    private final StorageService storageService;
    
    @Override
    public Photo uploadPhoto(MultipartFile file) {
        String photoId = UUID.randomUUID().toString();
        String url = storageService.store(file, photoId);

        return Photo.builder()
        .url(url)
        .uploadDate(LocalDateTime.now())
        .build();
    }

    @Override
    public Optional<Resource> getPhotoAsResources(String id) {
        return storageService.loadAsResource(id);
    }

}
