package edu.iff.project.sustainableconnection.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.iff.project.sustainableconnection.DTO.ClientDTO;
import edu.iff.project.sustainableconnection.model.User;
import edu.iff.project.sustainableconnection.model.Client;
import edu.iff.project.sustainableconnection.model.UserRole;
import edu.iff.project.sustainableconnection.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/client/update")
    public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO body,
                                            Authentication authentication) {
        User authenticatedUser = (User) authentication.getPrincipal();

        Long id = authenticatedUser.getId();

        Optional<Client> updatedUser = userService.update(id, body.avatar(), body.name(), body.matriculation());

        return updatedUser
                .map(ClientDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id, Authentication authentication) {
        User authenticatedUser = (User) authentication.getPrincipal();

        boolean isAdmin = authenticatedUser.getRole() == UserRole.ADMIN;
        boolean isSelf = authenticatedUser.getId().equals(id);

        if (!isAdmin && !isSelf) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public ResponseEntity<ClientDTO> getAuthenticatedUser(Authentication authentication) {
        User authenticatedUser = (User) authentication.getPrincipal();

        Optional<Client> client = userService.findClientById(authenticatedUser.getId());

        return client
                .map(ClientDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/top-points")
    public List<ClientDTO> getTop10Clients() {
        return userService.getTop10ClientsByPoints();
    }
}

