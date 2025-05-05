package edu.iff.project.sustainableconnection.controller;

import edu.iff.project.sustainableconnection.DTO.AddressDTO;
import edu.iff.project.sustainableconnection.model.Address;
import edu.iff.project.sustainableconnection.service.AddressService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public List<Address> getAll() {

        return addressService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getById(@PathVariable @Valid Long id) {

        Optional<Address> address = addressService.findById(id);
        return address.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Address> create(@RequestBody @Valid AddressDTO body) {
        Address savedAddress = addressService.save(
            body.street(),
            body.city(),
            body.state(),
            body.zipCode(),
            body.latitude(),
            body.longitude()
        );
        return ResponseEntity.ok(savedAddress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@PathVariable Long id, @RequestBody AddressDTO body) {
        Address updatedAddress = addressService.update(
            id, 
            body.street(),
            body.city(),
            body.state(),
            body.zipCode(),
            body.latitude(),
            body.longitude()
        );
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        boolean deleted = addressService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
