package edu.iff.project.sustainableconnection.controller;

import edu.iff.project.sustainableconnection.model.RedemptionRecord;
import edu.iff.project.sustainableconnection.service.RedemptionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/redemptions")
public class RedemptionRecordController {

    @Autowired
    private RedemptionRecordService redemptionRecordService;

    @GetMapping("/getAll")
    public ResponseEntity<List<RedemptionRecord>> getAll() {
        List<RedemptionRecord> records = redemptionRecordService.findAll();
        return ResponseEntity.ok(records);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RedemptionRecord> getById(@PathVariable Long id) {
        Optional<RedemptionRecord> record = redemptionRecordService.findById(id);
        return record.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/post")
    public ResponseEntity<RedemptionRecord> create(@RequestParam Long userId, @RequestParam Long rewardItemId) {
        try {
            RedemptionRecord savedRecord = redemptionRecordService.save(userId, rewardItemId);
            return ResponseEntity.ok(savedRecord);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<RedemptionRecord> update(
            @PathVariable Long id, 
            @RequestParam Long userId, 
            @RequestParam Long rewardItemId, 
            @RequestParam LocalDateTime redemptionDate) {

        try {
            RedemptionRecord updatedRecord = redemptionRecordService.update(id, userId, rewardItemId, redemptionDate);
            return (updatedRecord != null) ? ResponseEntity.ok(updatedRecord) : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = redemptionRecordService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
