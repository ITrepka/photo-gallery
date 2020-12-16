package com.itrepka.photogallery.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (HttpStatus.BAD_REQUEST)
public class PhotoInvalidDataException extends Exception {
    public PhotoInvalidDataException (String message) {
        super(message);
    }
}
