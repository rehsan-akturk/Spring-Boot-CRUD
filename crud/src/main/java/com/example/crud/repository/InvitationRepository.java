package com.example.crud.repository;

import com.example.crud.model.Invitation;
import com.example.crud.model.InvitationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InvitationRepository extends JpaRepository<Invitation, UUID> {

    Optional<Invitation> findByUserIdAndStatus(UUID userId, InvitationStatus status);

    Invitation findByUserId(UUID userId);
}
