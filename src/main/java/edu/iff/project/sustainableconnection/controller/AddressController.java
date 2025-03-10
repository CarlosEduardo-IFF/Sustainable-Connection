package edu.iff.project.sustainableconnection.controller;

import edu.iff.project.sustainableconnection.model.Address;
import edu.iff.project.sustainableconnection.service.AddressService;
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

    @GetMapping("/getAll")
    public List<Address> getAll() {

        return addressService.findAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Address> getById(@PathVariable Long id) {

        Optional<Address> address = addressService.findById(id);
        return address.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/post")
    public ResponseEntity<Address> create(@RequestParam String street, @RequestParam String city, @RequestParam String state,
            @RequestParam String zipCode) {

        Address savedAddress = addressService.save(street, city, state, zipCode);
        return ResponseEntity.ok(savedAddress);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Address> update(@PathVariable Long id, @RequestParam String street, @RequestParam String city,
            @RequestParam String state, @RequestParam String zipCode) {

        Optional<Address> updatedAddress = addressService.update(id, street, city, state, zipCode);
        return updatedAddress.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        boolean deleted = addressService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
