package com.itrepka.photogallery.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (HttpStatus.BAD_REQUEST)
public class GalleryInvalidDataException extends Exception {
    public GalleryInvalidDataException (String message) {
        super(message);
    }
}
