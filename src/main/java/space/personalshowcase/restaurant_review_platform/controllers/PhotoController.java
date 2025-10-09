package space.personalshowcase.restaurant_review_platform.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import space.personalshowcase.restaurant_review_platform.domain.entities.Photo;
import space.personalshowcase.restaurant_review_platform.domain.entities.dtos.PhotoDto;
import space.personalshowcase.restaurant_review_platform.mappers.PhotoMapper;
import space.personalshowcase.restaurant_review_platform.services.PhotoService;

@RestController
@RequestMapping(path = "/api/photos")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;
    private final PhotoMapper photoMapper;

    @PostMapping
    public ResponseEntity<PhotoDto> uploadPhoto(@RequestParam("file")MultipartFile file)
    {
        Photo savePhoto = photoService.uploadPhoto(file);
        PhotoDto dto = photoMapper.toDto(savePhoto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("{id:.+}")
    public ResponseEntity<Resource> getPhoto(@PathVariable String id) {
        return photoService.getPhotoAsResources(id).map(photo ->
             ResponseEntity.ok()
                    .contentType(
                            MediaTypeFactory.getMediaType(photo)
                                .orElse(MediaType.APPLICATION_OCTET_STREAM))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                    .body(photo))
                .orElse(ResponseEntity.notFound().build());
    }
}
