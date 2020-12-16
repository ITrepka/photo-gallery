package com.itrepka.photogallery.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GalleryNotFoundException extends Exception{
    public GalleryNotFoundException (String message) {
        super(message);
    }
}
