package edu.iff.project.sustainableconnection.controller;

import edu.iff.project.sustainableconnection.model.RewardItem;
import edu.iff.project.sustainableconnection.service.RewardItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reward-items")
public class RewardItemController {

    @Autowired
    private RewardItemService rewardItemService;

    @GetMapping("/getAll")
    public ResponseEntity<List<RewardItem>> getAll() {
        List<RewardItem> rewardItems = rewardItemService.findAll();
        return ResponseEntity.ok(rewardItems);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RewardItem> getById(@PathVariable Long id) {
        Optional<RewardItem> rewardItem = rewardItemService.findById(id);
        return rewardItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/post")
    public ResponseEntity<RewardItem> create(@RequestParam String name, @RequestParam String description,
                                             @RequestParam int costInPoints, @RequestParam int quantity) {
        RewardItem savedRewardItem = rewardItemService.save(name, description, costInPoints, quantity);
        return ResponseEntity.ok(savedRewardItem);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<RewardItem> update(@PathVariable Long id, @RequestParam String name,
                                             @RequestParam String description, @RequestParam int costInPoints,
                                             @RequestParam int quantity) {
        RewardItem updatedRewardItem = rewardItemService.update(id, name, description, costInPoints, quantity);
        return (updatedRewardItem != null) ? ResponseEntity.ok(updatedRewardItem) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = rewardItemService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

