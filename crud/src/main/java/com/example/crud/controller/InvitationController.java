package com.example.crud.controller;


import com.example.crud.model.Invitation;
import com.example.crud.service.InvitationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/invitations")
public class InvitationController {

    private final InvitationService invitationService;

    public InvitationController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @GetMapping
    public ResponseEntity<List<Invitation>> getAllInvitations() {
        List<Invitation> invitations = invitationService.findAll();
        return ResponseEntity.ok(invitations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invitation> getInvitationById(@PathVariable UUID id) {
        Invitation invitation = invitationService.findById(id);
        return ResponseEntity.ok(invitation);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Invitation> getInvitationByUserId(@PathVariable UUID userId) {
        Invitation invitation = invitationService.findByUserId(userId);
        return ResponseEntity.ok(invitation);
    }

    @PostMapping
    public ResponseEntity<Invitation> createInvitation(@RequestBody Invitation invitation) {
        Invitation createdInvitation = invitationService.create(invitation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInvitation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invitation> updateInvitation(@PathVariable UUID id, @RequestBody Invitation invitation) {
        Invitation updatedInvitation = invitationService.update(id, invitation);
        return ResponseEntity.ok(updatedInvitation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvitation(@PathVariable UUID id) {
        invitationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}