package com.example.crud.service;

import com.example.crud.exception.InvitationNotFoundException;
import com.example.crud.model.Invitation;
import com.example.crud.model.InvitationStatus;
import com.example.crud.model.User;
import com.example.crud.repository.InvitationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InvitationService {

    private final InvitationRepository invitationRepository;

    public InvitationService(InvitationRepository invitationRepository) {
        this.invitationRepository = invitationRepository;
    }

    public Invitation create(Invitation invitation) {
        User user = invitation.getUser();

        if (user.getInvitation() != null && user.getInvitation().getStatus() == InvitationStatus.PENDING) {
            throw new IllegalStateException("User already has a pending invitation");
        }

        if (user.getInvitation() != null && user.getInvitation().getStatus() == InvitationStatus.REJECTED) {
            throw new IllegalStateException("User cannot be reinvited after the invitation is rejected");
        }

        return invitationRepository.save(invitation);
    }

    public Invitation findById(UUID id) {
        return invitationRepository.findById(id)
                .orElseThrow(() -> new InvitationNotFoundException(id));
    }

    public List<Invitation> findAll() {
        return invitationRepository.findAll();
    }

    public Invitation update(UUID id, Invitation invitation) {
        Invitation existingInvitation = findById(id);

        existingInvitation.setUser(invitation.getUser());
        existingInvitation.setInvitationMessage(invitation.getInvitationMessage());
        existingInvitation.setStatus(invitation.getStatus());

        return invitationRepository.save(existingInvitation);
    }

    public void delete(UUID id) {
        invitationRepository.delete(findById(id));
    }

    public Invitation findByUserId(UUID userId) {
        Invitation invitation = invitationRepository.findByUserId(userId);

        if (invitation == null) {
            throw new InvitationNotFoundException( userId);
        }

        return invitation;
    }

}
