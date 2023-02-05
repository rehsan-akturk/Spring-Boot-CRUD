package com.example.crud.repository;

import com.example.crud.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
    boolean existsByRegistryNumber(String registryNumber);
    Optional<Organization> findByRegistryNumber(String registryNumber);
    List<Organization> findByNormalizedNameContaining(String normalizedName);
    List<Organization> findByUsers_Id(UUID userId);
}