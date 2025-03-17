package edu.iff.project.sustainableconnection.service;

import edu.iff.project.sustainableconnection.model.RewardItem;
import edu.iff.project.sustainableconnection.repository.RewardItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RewardItemService {

    @Autowired
    private RewardItemRepository rewardItemRepository;

    public List<RewardItem> findAll() {
        return rewardItemRepository.findAll();
    }

    public Optional<RewardItem> findById(Long id) {
        return rewardItemRepository.findById(id);
    }

    public RewardItem save(String name, String description, int costInPoints, int quantity) {
        RewardItem rewardItem = new RewardItem(name, description, costInPoints, quantity);
        return rewardItemRepository.save(rewardItem);
    }

    public RewardItem update(Long id, String name, String description, int costInPoints, int quantity) {
        if (!rewardItemRepository.existsById(id)) {
            return null;
        }

        RewardItem rewardItem = new RewardItem(name, description, costInPoints, quantity);
        rewardItem.setId(id);
        return rewardItemRepository.save(rewardItem);
    }

    public boolean delete(Long id) {
        if (rewardItemRepository.existsById(id)) {
            rewardItemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
