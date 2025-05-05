package edu.iff.project.sustainableconnection.controller;

import edu.iff.project.sustainableconnection.DTO.DiscardedItemDTO;
import edu.iff.project.sustainableconnection.DTO.DiscardedItemDTOResponse;
import edu.iff.project.sustainableconnection.model.DiscardedItem;
import edu.iff.project.sustainableconnection.service.DiscardedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discarded-items")
public class DiscardedItemController {

    @Autowired
    private DiscardedItemService discardedItemService;

    @GetMapping
    public ResponseEntity<List<DiscardedItem>> getAll() {
        List<DiscardedItem> discardedItems = discardedItemService.findAll();
        return ResponseEntity.ok(discardedItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscardedItem> getById(@PathVariable Long id) {
        return discardedItemService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DiscardedItem> create(@RequestBody DiscardedItemDTO body) {
        try {
            DiscardedItem saved = discardedItemService.save(
                body.email(), body.dropOffPointId(), body.categoryId()
            );
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = discardedItemService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/by-client")
    public ResponseEntity<List<DiscardedItemDTOResponse>> getByClientEmail(@RequestParam String email) {
        try {
            List<DiscardedItem> items = discardedItemService.findByClientEmail(email);

            List<DiscardedItemDTOResponse> dtoList = items.stream().map(item ->
                new DiscardedItemDTOResponse(
                    item.getId(),
                    item.getDiscardDate().toLocalDate().toString(),
                    item.getCategory().getName(),
                    item.getDropOffPoint().getName(),
                    item.getDropOffPoint().getDescription(),
                    item.getPointsEarned()
                )
            ).toList();

            return ResponseEntity.ok(dtoList);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
