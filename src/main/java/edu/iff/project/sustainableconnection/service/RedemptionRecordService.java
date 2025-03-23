package edu.iff.project.sustainableconnection.service;

import edu.iff.project.sustainableconnection.model.RedemptionRecord;
import edu.iff.project.sustainableconnection.model.User;
import edu.iff.project.sustainableconnection.model.RewardItem;
import edu.iff.project.sustainableconnection.repository.RedemptionRecordRepository;
import edu.iff.project.sustainableconnection.repository.RewardItemRepository;
import edu.iff.project.sustainableconnection.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RedemptionRecordService {

	@Autowired
    private RedemptionRecordRepository redemptionRecordRepository;

    @Autowired
    private UserRepository userRepository; 

    @Autowired
    private RewardItemRepository rewardItemRepository;  

    
    public List<RedemptionRecord> findAll() {
        return redemptionRecordRepository.findAll();
    }

    public Optional<RedemptionRecord> findById(Long id) {
        return redemptionRecordRepository.findById(id);
    }

    public RedemptionRecord save(Long userId, Long rewardItemId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<RewardItem> rewardItemOpt = rewardItemRepository.findById(rewardItemId);

        if (userOpt.isEmpty() || rewardItemOpt.isEmpty()) {
            throw new IllegalArgumentException("Usuário ou recompensa não encontrados.");
        }

        User user = userOpt.get();
        RewardItem rewardItem = rewardItemOpt.get();

        RedemptionRecord redemptionRecord = new RedemptionRecord(user, rewardItem, LocalDateTime.now());
        return redemptionRecordRepository.save(redemptionRecord);
    }

    public RedemptionRecord update(Long id, Long userId, Long rewardItemId, LocalDateTime redemptionDate) {
        
        Optional<RedemptionRecord> existingRecordOpt = redemptionRecordRepository.findById(id);
        if (existingRecordOpt.isEmpty()) {
            return null;
        }
   
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<RewardItem> rewardItemOpt = rewardItemRepository.findById(rewardItemId);

        if (userOpt.isEmpty() || rewardItemOpt.isEmpty()) {
            throw new IllegalArgumentException("Usuário ou recompensa não encontrados.");
        }

        User user = userOpt.get();
        RewardItem rewardItem = rewardItemOpt.get();

        RedemptionRecord record = existingRecordOpt.get();
        record.setUser(user);
        record.setRewardItem(rewardItem);
        record.setRedempionDate(redemptionDate);

        return redemptionRecordRepository.save(record);
    }

    public boolean delete(Long id) {
        if (redemptionRecordRepository.existsById(id)) {
            redemptionRecordRepository.deleteById(id);
            return true;
        }
        return false;
    }
}