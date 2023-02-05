package com.example.crud.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;



@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "created_by")
    private UUID createdBy;

    @Column(name = "updated_by")
    private UUID updatedBy;

    public  BaseEntity(){

    }

    public BaseEntity(UUID id, LocalDateTime created, LocalDateTime updated, UUID createdBy, UUID updatedBy) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public UUID getId() {
        return id;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public UUID getUpdatedBy() {
        return updatedBy;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}