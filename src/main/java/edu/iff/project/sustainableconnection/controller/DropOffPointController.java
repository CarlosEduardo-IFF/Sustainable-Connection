package edu.iff.project.sustainableconnection.controller;

import edu.iff.project.sustainableconnection.model.DropOffPoint;
import edu.iff.project.sustainableconnection.service.DropOffPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/drop-off-points")
public class DropOffPointController {

    @Autowired
    private DropOffPointService dropOffPointService;

    @GetMapping("/getAll")
    public ResponseEntity<List<DropOffPoint>> getAll() {

        List<DropOffPoint> dropOffPoints = dropOffPointService.findAll();
        return ResponseEntity.ok(dropOffPoints);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DropOffPoint> getById(@PathVariable Long id) {

        Optional<DropOffPoint> dropOffPoint = dropOffPointService.findById(id);
        return dropOffPoint.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/post")
    public ResponseEntity<DropOffPoint> create(@RequestParam String name, @RequestParam String description, @RequestParam Long addressId) {

        try {
            DropOffPoint savedDropOffPoint = dropOffPointService.save(name, description, addressId);
            return ResponseEntity.ok(savedDropOffPoint);

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<DropOffPoint> update(@PathVariable Long id, @RequestParam String name, @RequestParam String description,
            @RequestParam Long addressId) {

        try {

            DropOffPoint updatedDropOffPoint = dropOffPointService.update(id, name, description, addressId);
            return (updatedDropOffPoint != null) ? ResponseEntity.ok(updatedDropOffPoint): ResponseEntity.notFound().build();

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        boolean deleted = dropOffPointService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
