package com.example.crud.controller;

import com.example.crud.exception.OrganizationNotFoundException;
import com.example.crud.model.Organization;
import com.example.crud.model.User;
import com.example.crud.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    public ResponseEntity<Organization> createOrganization(@RequestBody Organization organization) {
        try {
            Organization createdOrganization = organizationService.create(organization);
            return new ResponseEntity<>(createdOrganization, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organization> getOrganization(@PathVariable UUID id) {
        try {
            Organization organization = organizationService.get(id);
            return new ResponseEntity<>(organization, HttpStatus.OK);
        } catch (OrganizationNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Organization>> getAllOrganizations() {
        List<Organization> organizations = organizationService.getAll();
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    @GetMapping("/search/normalizedName/{normalizedName}")
    public ResponseEntity<List<Organization>> searchByNormalizedName(@PathVariable String normalizedName) {
        List<Organization> organizations = organizationService.searchByNormalizedName(normalizedName);
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    @GetMapping("/search/registryNumber/{registryNumber}")
    public ResponseEntity<Organization> searchByRegistryNumber(@PathVariable String registryNumber) {
        try {
            Organization organization = organizationService.searchByRegistryNumber(registryNumber);
            return new ResponseEntity<>(organization, HttpStatus.OK);
        } catch (OrganizationNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<List<User>> getUsersForOrganization(@PathVariable UUID id) {
        try {
            List<User> users = organizationService.getUsersForOrganization(id);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (OrganizationNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}

