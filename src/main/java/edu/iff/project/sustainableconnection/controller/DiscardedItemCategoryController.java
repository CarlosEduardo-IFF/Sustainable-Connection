package edu.iff.project.sustainableconnection.controller;

import edu.iff.project.sustainableconnection.DTO.DiscardedItemCategoryDTO;
import edu.iff.project.sustainableconnection.model.DiscardedItemCategory;
import edu.iff.project.sustainableconnection.service.DiscardedItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/discarded-item-categories")
public class DiscardedItemCategoryController {

    @Autowired
    private DiscardedItemCategoryService discardedItemCategoryService;

    @GetMapping
    public ResponseEntity<List<DiscardedItemCategory>> getAll() {
        List<DiscardedItemCategory> categories = discardedItemCategoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscardedItemCategory> getById(@PathVariable Long id) {
        Optional<DiscardedItemCategory> category = discardedItemCategoryService.findById(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DiscardedItemCategory> create(@RequestBody DiscardedItemCategoryDTO body) {
        try {
            DiscardedItemCategory savedCategory = discardedItemCategoryService.save(body.name(), body.pointsPerItem());
            return ResponseEntity.ok(savedCategory);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiscardedItemCategory> update(@PathVariable Long id, @RequestBody DiscardedItemCategoryDTO body) {
        try {
            DiscardedItemCategory updatedCategory = discardedItemCategoryService.update(id, body.name(), body.pointsPerItem());
            return (updatedCategory != null) ? ResponseEntity.ok(updatedCategory) : ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = discardedItemCategoryService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}