package edu.iff.project.sustainableconnection.controller;

import edu.iff.project.sustainableconnection.model.DiscardedItem;
import edu.iff.project.sustainableconnection.service.DiscardedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/discarded-items")
public class DiscardedItemController {

    @Autowired
    private DiscardedItemService discardedItemService;

    @GetMapping("/getAll")
    public ResponseEntity<List<DiscardedItem>> getAll() {
        List<DiscardedItem> discardedItems = discardedItemService.findAll();
        return ResponseEntity.ok(discardedItems);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DiscardedItem> getById(@PathVariable Long id) {
        Optional<DiscardedItem> discardedItem = discardedItemService.findById(id);
        return discardedItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/post")
    public ResponseEntity<DiscardedItem> create(@RequestParam Long userId, @RequestParam Long dropOffPointId, 
                                                 @RequestParam Long categoryId, @RequestParam int pointsEarned) {
        try {
            DiscardedItem savedDiscardedItem = discardedItemService.save(userId, dropOffPointId, categoryId, pointsEarned);
            return ResponseEntity.ok(savedDiscardedItem);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<DiscardedItem> update(@PathVariable Long id, @RequestParam Long userId, 
                                                 @RequestParam Long dropOffPointId, @RequestParam Long categoryId, 
                                                 @RequestParam int pointsEarned) {
        try {
            DiscardedItem updatedDiscardedItem = discardedItemService.update(id, userId, dropOffPointId, categoryId, pointsEarned);
            return (updatedDiscardedItem != null) ? ResponseEntity.ok(updatedDiscardedItem) : ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = discardedItemService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
