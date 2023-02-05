package com.example.crud.controller;

import com.example.crud.model.Organization;
import com.example.crud.model.User;
import com.example.crud.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/search/normalizedName/{normalizedName}")
    public ResponseEntity<List<User>> searchByNormalizedName(@PathVariable String normalizedName) {
        List<User> users = userService.searchByNormalizedName(normalizedName);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/search/email/{email}")
    public ResponseEntity<User> searchByEmail(@PathVariable String email) {
        User user = userService.searchByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}/organizations")
    public ResponseEntity<List<Organization>> getOrganizationsForUser(@PathVariable UUID id) {
        List<Organization> organizations = userService.getOrganizationsForUser(id);
        return ResponseEntity.ok(organizations);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User user) {
        User updatedUser = userService.update(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
