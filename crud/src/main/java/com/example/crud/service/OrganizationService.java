package com.example.crud.service;

import com.example.crud.model.Organization;
import com.example.crud.model.User;
import com.example.crud.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.example.crud.exception.OrganizationNotFoundException;
@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    public Organization createOrganization(Organization organization) {
        Optional<Organization> existingOrganization = organizationRepository.findByRegistryNumber(organization.getRegistryNumber());

        if (existingOrganization.isPresent()) {
            throw new IllegalStateException("Registry number is already in use");
        }

        return organizationRepository.save(organization);
    }
    public List<Organization> getAll() {
        return organizationRepository.findAll();
    }

    public Optional<Organization> findOrganizationByRegistryNumber(String registryNumber) {
        return organizationRepository.findByRegistryNumber(registryNumber);
    }

    public List<Organization> findOrganizationsByNormalizedName(String normalizedName) {
        return organizationRepository.findByNormalizedNameContaining(normalizedName);
    }

    public List<User> getUsersForOrganization(UUID id) {
        Organization organization = findOrganizationById(id);
        return new ArrayList<>(organization.getUsers());
    }


    private Organization findOrganizationById(UUID id) {
        Optional<Organization> organization = organizationRepository.findById(id);

        if (!organization.isPresent()) {
            throw new IllegalArgumentException("Organization not found");
        }

        return organization.get();
    }

    public List<Organization> searchByNormalizedName(String normalizedName) {
        return organizationRepository.findByNormalizedNameContaining(normalizedName);
    }

    public Organization searchByRegistryNumber(String registryNumber) {
        Optional<Organization> organization = organizationRepository.findByRegistryNumber(registryNumber);
        if (organization.isPresent()) {
            return organization.get();
        } else {
            throw new OrganizationNotFoundException("Organization with registry number " + registryNumber + " was not found.");
        }
    }

    public Organization get(UUID id) {
        return organizationRepository.findById(id)
                .orElseThrow(() ->  new OrganizationNotFoundException(id.toString()));
    }

    public Organization create(Organization organization) {
        if (organizationRepository.existsByRegistryNumber(organization.getRegistryNumber())) {
            throw new IllegalArgumentException("Registry number already in use");
        }
        return organizationRepository.save(organization);
    }

}