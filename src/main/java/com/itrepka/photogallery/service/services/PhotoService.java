package com.itrepka.photogallery.service.services;

import com.itrepka.photogallery.model.Gallery;
import com.itrepka.photogallery.model.Photo;
import com.itrepka.photogallery.repository.GalleryRepository;
import com.itrepka.photogallery.repository.PhotoRepository;
import com.itrepka.photogallery.service.dto.CreatePhotoDto;
import com.itrepka.photogallery.service.dto.PhotoDto;
import com.itrepka.photogallery.service.dto.UpdatePhotoDto;
import com.itrepka.photogallery.service.exception.GalleryNotFoundException;
import com.itrepka.photogallery.service.exception.PhotoInvalidDataException;
import com.itrepka.photogallery.service.exception.PhotoNotFoundException;
import com.itrepka.photogallery.service.exception.UserNotFoundException;
import com.itrepka.photogallery.service.mapper.PhotoDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private PhotoDtoMapper photoDtoMapper;
    @Autowired
    private GalleryRepository galleryRepository;

    public List<PhotoDto> getAllPhotos() {
        return photoRepository.findAll().stream()
                .map(photo -> photoDtoMapper.toDto(photo))
                .collect(Collectors.toList());
    }

    public PhotoDto getPhotoById(int id) throws PhotoNotFoundException {
        return photoRepository.findById(id)
                .map(photo -> photoDtoMapper.toDto(photo))
                .orElseThrow(() -> new PhotoNotFoundException("Not found photo with id = " + id));
    }

    @Transactional
    public PhotoDto addNewPhoto(CreatePhotoDto createPhotoDto) throws PhotoInvalidDataException, UserNotFoundException, GalleryNotFoundException {
        Photo photo = photoDtoMapper.toModel(createPhotoDto);
        photo.setCreatedAt(OffsetDateTime.now());

        Integer galleryId = createPhotoDto.getGalleryId();

        if (galleryId == null) {
            throw new PhotoInvalidDataException("Gallery id must be given while photo is creating");
        }

        Gallery gallery = galleryRepository.findById(galleryId)
                .orElseThrow(() -> new GalleryNotFoundException("Not Found gallery with id = " + galleryId));

        photo.setGallery(gallery);

        Photo savedPhoto = photoRepository.save(photo);
        //todo check is photo added to gallery too

        return photoDtoMapper.toDto(savedPhoto);
    }

    @Transactional
    public PhotoDto updatePhoto(int id, UpdatePhotoDto updatePhotoDto) throws PhotoNotFoundException {

        Photo photo = photoRepository.findById(id)
                .orElseThrow(() -> new PhotoNotFoundException("Not found photo with id = " + id));

        photo.setName(updatePhotoDto.getName());
        photo.setUpdatedAt(OffsetDateTime.now());
        Photo updatedPhoto = photoRepository.save(photo);

        return photoDtoMapper.toDto(updatedPhoto);
    }

    @Transactional
    public PhotoDto deletePhotoById(int id) throws PhotoNotFoundException {
        Photo photo = photoRepository.findById(id)
                .orElseThrow(() -> new PhotoNotFoundException("Not found photo with id = " + id));

        photoRepository.deleteById(id);

        return photoDtoMapper.toDto(photo);
    }
}
