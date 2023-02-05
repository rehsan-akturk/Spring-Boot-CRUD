package com.example.crud.service;

import com.example.crud.model.Organization;
import com.example.crud.model.User;
import com.example.crud.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
    }

    public List<User> searchByNormalizedName(String normalizedName) {
        return userRepository.findByNormalizedNameContaining(normalizedName);
    }

    public User searchByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElseThrow(() -> new EntityNotFoundException("User not found with email " + email));
    }

    public List<Organization> getOrganizationsForUser(UUID id) {
        User user = findById(id);
        return new ArrayList<>(user.getOrganizations());
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(UUID id, User updatedUser) {
        User user = findById(id);
        user.setFullName(updatedUser.getFullName());
        user.setEmail(updatedUser.getEmail());
        user.setNormalizedName(updatedUser.getNormalizedName());
        user.setOrganizations(updatedUser.getOrganizations());
        return userRepository.save(user);
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}