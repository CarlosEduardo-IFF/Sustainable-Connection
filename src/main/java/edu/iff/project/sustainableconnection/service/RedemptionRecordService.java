package edu.iff.project.sustainableconnection.service;

import edu.iff.project.sustainableconnection.DTO.RedemptionRecordDTOResponse;
import edu.iff.project.sustainableconnection.model.Client;
import edu.iff.project.sustainableconnection.model.RedemptionRecord;
import edu.iff.project.sustainableconnection.model.User;
import edu.iff.project.sustainableconnection.model.RewardItem;
import edu.iff.project.sustainableconnection.repository.ClientRepository;
import edu.iff.project.sustainableconnection.repository.RedemptionRecordRepository;
import edu.iff.project.sustainableconnection.repository.RewardItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RedemptionRecordService {

	@Autowired
    private RedemptionRecordRepository redemptionRecordRepository;

    @Autowired
    private ClientRepository clientRepository; 

    @Autowired
    private UserService userService;

    @Autowired
    private RewardItemRepository rewardItemRepository;  

    
    public List<RedemptionRecord> findAll() {
        return redemptionRecordRepository.findAll();
    }

    public Optional<RedemptionRecord> findById(Long id) {
        return redemptionRecordRepository.findById(id);
    }

    public RedemptionRecord save(String email, Long rewardItemId) {
        Optional<Client> userOpt = clientRepository.findByEmail(email);
        Optional<RewardItem> rewardItemOpt = rewardItemRepository.findById(rewardItemId);
    
        if (userOpt.isEmpty() || rewardItemOpt.isEmpty()) {
            throw new IllegalArgumentException("Usuário ou recompensa não encontrados.");
        }
    
        Client user = userOpt.get();
        RewardItem rewardItem = rewardItemOpt.get();
    
        if (user.getCredits() < rewardItem.getCostInPoints()) {
            throw new IllegalArgumentException("Créditos insuficientes.");
        }
    
        if (rewardItem.getQuantity() < 1) {
            throw new IllegalArgumentException("Recompensa indisponível.");
        }
    
        user.setCredits(user.getCredits() - rewardItem.getCostInPoints());
        rewardItem.setQuantity(rewardItem.getQuantity() - 1);
    
        clientRepository.save(user);
        rewardItemRepository.save(rewardItem);
    
        RedemptionRecord redemptionRecord = new RedemptionRecord(user, rewardItem, LocalDateTime.now());
        return redemptionRecordRepository.save(redemptionRecord);
    }

    public RedemptionRecord update(Long id, Long userId, Long rewardItemId, LocalDateTime redemptionDate) {
        
        Optional<RedemptionRecord> existingRecordOpt = redemptionRecordRepository.findById(id);
        if (existingRecordOpt.isEmpty()) {
            return null;
        }
   
        Optional<Client> userOpt = clientRepository.findById(userId);
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

    public List<RedemptionRecordDTOResponse> getRecordsByUserEmail(String email) {
        User user = (User) userService.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("Usuário não encontrado com o e-mail: " + email);
        }

        Long idUser = user.getId();
        return redemptionRecordRepository.findByUserId(idUser)
                .stream()
                .map(record -> new RedemptionRecordDTOResponse(
                    record.getRewardItem().getName(),
                    record.getRewardItem().getDescription(),
                    record.getRewardItem().getCostInPoints(),
                    record.getRedempionDate(), 
                    record.getRewardItem().getId()))
                .collect(Collectors.toList());
    }
}