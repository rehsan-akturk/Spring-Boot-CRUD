package com.example.crud.repository;

import com.example.crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);
    List<User> findByNormalizedNameContaining(String normalizedName);
    List<User> findByOrganizations_Id(UUID organizationId);
}