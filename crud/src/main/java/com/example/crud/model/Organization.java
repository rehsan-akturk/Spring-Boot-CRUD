package com.example.crud.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "organization")
public class Organization extends BaseEntity {
    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "normalizedName")
    private String normalizedName;

    @Column(name = "registry_number", unique = true)
    private String registryNumber;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "year_founded")
    private Integer yearFounded;

    @Column(name = "phone")
    private String phone;

    @Column(name = "company_size")
    private Integer companySize;

    @ManyToMany(mappedBy = "organizations")
    private Set<User> users = new HashSet<>();

    public Organization() {

    }
    public Organization(UUID id, LocalDateTime created, LocalDateTime updated, UUID createdBy, UUID updatedBy, String organizationName, String normalizedName, String registryNumber, String contactEmail, Integer yearFounded, String phone, Integer companySize, Set<User> users) {
        super(id, created, updated, createdBy, updatedBy);
        this.organizationName = organizationName;
        this.normalizedName = normalizedName;
        this.registryNumber = registryNumber;
        this.contactEmail = contactEmail;
        this.yearFounded = yearFounded;
        this.phone = phone;
        this.companySize = companySize;
        this.users = users;
    }

    public void setRegistryNumber(String registryNumber) {
        this.registryNumber = registryNumber;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getNormalizedName() {
        return normalizedName;
    }

    public void setNormalizedName(String normalizedName) {
        this.normalizedName = normalizedName;
    }

    public String getRegistryNumber() {
        return registryNumber;
    }



    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Integer getYearFounded() {
        return yearFounded;
    }

    public void setYearFounded(Integer yearFounded) {
        this.yearFounded = yearFounded;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCompanySize() {
        return companySize;
    }

    public void setCompanySize(Integer companySize) {
        this.companySize = companySize;
    }


}
