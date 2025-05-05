package edu.iff.project.sustainableconnection.controller;

import edu.iff.project.sustainableconnection.DTO.RewardItemDTO;
import edu.iff.project.sustainableconnection.model.RewardItem;
import edu.iff.project.sustainableconnection.service.RewardItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reward-items")
public class RewardItemController {

    @Autowired
    private RewardItemService rewardItemService;

    @GetMapping
    public ResponseEntity<List<RewardItem>> getAll() {
        List<RewardItem> rewardItems = rewardItemService.findAll();
        return ResponseEntity.ok(rewardItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RewardItem> getById(@PathVariable Long id) {
        return rewardItemService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RewardItem> create(@RequestBody RewardItemDTO body) {
        RewardItem saved = rewardItemService.save(
            body.name(),
            body.description(),
            body.costInPoints(),
            body.quantity()
        );
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RewardItem> update(@PathVariable Long id, @RequestBody RewardItemDTO body) {
        RewardItem updated = rewardItemService.update(
            id,
            body.name(),
            body.description(),
            body.costInPoints(),
            body.quantity()
        );
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = rewardItemService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

