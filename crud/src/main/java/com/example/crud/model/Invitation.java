package com.example.crud.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "invitation")
public class Invitation extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "invitation_message")
    private String invitationMessage;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private InvitationStatus status;

    public  Invitation(){

    }


    public Invitation(UUID id, LocalDateTime created, LocalDateTime updated, UUID createdBy, UUID updatedBy, User user, String invitationMessage, InvitationStatus status) {
        super(id, created, updated, createdBy, updatedBy);
        this.user = user;
        this.invitationMessage = invitationMessage;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getInvitationMessage() {
        return invitationMessage;
    }

    public void setInvitationMessage(String invitationMessage) {
        this.invitationMessage = invitationMessage;
    }

    public InvitationStatus getStatus() {
        return status;
    }

    public void setStatus(InvitationStatus status) {
        this.status = status;
    }
}

