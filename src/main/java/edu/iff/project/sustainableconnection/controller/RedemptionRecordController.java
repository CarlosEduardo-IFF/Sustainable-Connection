package edu.iff.project.sustainableconnection.controller;

import edu.iff.project.sustainableconnection.DTO.RedemptionRecordCreateDTO;
import edu.iff.project.sustainableconnection.DTO.RedemptionRecordDTOResponse;
import edu.iff.project.sustainableconnection.DTO.RedemptionRecordUpdateDTO;
import edu.iff.project.sustainableconnection.model.RedemptionRecord;
import edu.iff.project.sustainableconnection.service.RedemptionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/redemptions")
public class RedemptionRecordController {

    @Autowired
    private RedemptionRecordService redemptionRecordService;

    @GetMapping
    public ResponseEntity<List<RedemptionRecord>> getAll() {
        List<RedemptionRecord> records = redemptionRecordService.findAll();
        return ResponseEntity.ok(records);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RedemptionRecord> getById(@PathVariable Long id) {
        return redemptionRecordService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RedemptionRecord> create(@RequestBody RedemptionRecordCreateDTO body) {
        try {
            RedemptionRecord saved = redemptionRecordService.save(body.email(), body.rewardItemId());
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RedemptionRecord> update(@PathVariable Long id, @RequestBody RedemptionRecordUpdateDTO body) {
        try {
            RedemptionRecord updated = redemptionRecordService.update(
                id, body.userId(), body.rewardItemId(), body.redemptionDate()
            );
            return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = redemptionRecordService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/by-user-email")
    public List<RedemptionRecordDTOResponse> getRedemptionsByUserEmail(@RequestParam String email) {
        return redemptionRecordService.getRecordsByUserEmail(email);
    }
}
