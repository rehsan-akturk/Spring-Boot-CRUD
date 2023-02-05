package com.example.crud.model;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;



@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @OneToOne(mappedBy = "user")
    private Invitation invitation;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "normalized_name")
    private String normalizedName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_organization",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "organization_id"))
    private Set<Organization> organizations = new HashSet<>();

    public  User(){

    }
    public User(Invitation invitation, UserStatus status, String fullName, String email, String normalizedName, Set<Organization> organizations) {
        this.invitation = invitation;
        this.status = status;
        this.fullName = fullName;
        this.email = email;
        this.normalizedName = normalizedName;
        this.organizations = organizations;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNormalizedName() {
        return normalizedName;
    }

    public void setNormalizedName(String normalizedName) {
        this.normalizedName = normalizedName;
    }

    public Set<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(Set<Organization> organizations) {
        this.organizations = organizations;
    }

    public Invitation getInvitation() {
        return invitation;
    }
}