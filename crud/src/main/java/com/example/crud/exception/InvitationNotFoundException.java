package com.example.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

public class InvitationNotFoundException extends RuntimeException {
    public InvitationNotFoundException(UUID id) {
        super("Invitation not found with id: " + id);
    }
}
