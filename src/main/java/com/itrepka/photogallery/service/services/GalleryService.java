package com.itrepka.photogallery.service.services;

import com.itrepka.photogallery.model.Gallery;
import com.itrepka.photogallery.model.User;
import com.itrepka.photogallery.repository.GalleryRepository;
import com.itrepka.photogallery.repository.UserRepository;
import com.itrepka.photogallery.service.dto.CreateGalleryDto;
import com.itrepka.photogallery.service.dto.UpdateGalleryDto;
import com.itrepka.photogallery.service.dto.GalleryDto;
import com.itrepka.photogallery.service.exception.GalleryInvalidDataException;
import com.itrepka.photogallery.service.exception.GalleryNotFoundException;
import com.itrepka.photogallery.service.exception.UserNotFoundException;
import com.itrepka.photogallery.service.mapper.GalleryDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GalleryService {
    @Autowired
    private GalleryRepository galleryRepository;
    @Autowired
    private GalleryDtoMapper galleryDtoMapper;
    @Autowired
    private UserRepository userRepository;

    public List<GalleryDto> getAllGalleries() {
        return galleryRepository.findAll().stream()
                .map(gallery -> galleryDtoMapper.toDto(gallery))
                .collect(Collectors.toList());
    }

    public GalleryDto getGalleryById(int id) throws GalleryNotFoundException {
        return galleryRepository.findById(id)
                .map(gallery -> galleryDtoMapper.toDto(gallery))
                .orElseThrow(() -> new GalleryNotFoundException("Not found gallery with id = " + id));
    }

    @Transactional
    public GalleryDto addNewGallery(CreateGalleryDto createGalleryDto) throws GalleryInvalidDataException, UserNotFoundException {
        validateGalleryName(createGalleryDto.getName());

        Gallery gallery = galleryDtoMapper.toModel(createGalleryDto);
        gallery.setCreatedAt(OffsetDateTime.now());

        Integer userId = createGalleryDto.getUserId();
        if (userId == null) {
            throw new GalleryInvalidDataException("User must be given while gallery is creating");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Not found user with id = " + userId));
        gallery.setUser(user);
        Gallery savedGallery = galleryRepository.save(gallery);
        //todo check is gallery added to user already
        user.getUserGalleries().add(savedGallery);
        userRepository.save(user);

        return galleryDtoMapper.toDto(savedGallery);
    }

    @Transactional
    public GalleryDto updateGallery(int id, UpdateGalleryDto updateGalleryDto) throws GalleryInvalidDataException, GalleryNotFoundException {
        validateGalleryName(updateGalleryDto.getName());

        Gallery gallery = galleryRepository.findById(id)
                .orElseThrow(() -> new GalleryNotFoundException("Not found gallery with id = " + id));

        gallery.setName(updateGalleryDto.getName());
        gallery.setUpdatedAt(OffsetDateTime.now());
        Gallery updatedGallery = galleryRepository.save(gallery);

        return galleryDtoMapper.toDto(updatedGallery);
    }

    @Transactional
    public GalleryDto deleteGalleryById(int id) throws GalleryNotFoundException {
        Gallery gallery = galleryRepository.findById(id)
                .orElseThrow(() -> new GalleryNotFoundException("Not found gallery with id = " + id));

        galleryRepository.deleteById(id);

        return galleryDtoMapper.toDto(gallery);
    }


    private void validateGalleryName(String name) throws GalleryInvalidDataException {
        if (name == null || name.isEmpty()) {
            throw new GalleryInvalidDataException("Gallery name may not be empty or null");
        }
    }
}
