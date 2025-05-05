package edu.iff.project.sustainableconnection.controller;

import edu.iff.project.sustainableconnection.DTO.DropOffPointDTO;
import edu.iff.project.sustainableconnection.model.DropOffPoint;
import edu.iff.project.sustainableconnection.service.DropOffPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drop-off-points")
public class DropOffPointController {

    @Autowired
    private DropOffPointService dropOffPointService;

    @GetMapping
    public ResponseEntity<List<DropOffPoint>> getAll() {
        List<DropOffPoint> dropOffPoints = dropOffPointService.findAll();
        return ResponseEntity.ok(dropOffPoints);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DropOffPoint> getById(@PathVariable Long id) {
        return dropOffPointService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DropOffPoint> create(@RequestBody DropOffPointDTO body) {
        try {
            DropOffPoint saved = dropOffPointService.save(
                body.name(), body.description(), body.addressId()
            );
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DropOffPoint> update(@PathVariable Long id, @RequestBody DropOffPointDTO body) {
        try {
            DropOffPoint updated = dropOffPointService.update(
                id, body.name(), body.description(), body.addressId()
            );
            return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = dropOffPointService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

