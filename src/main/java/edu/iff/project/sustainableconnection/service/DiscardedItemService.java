package edu.iff.project.sustainableconnection.service;

import edu.iff.project.sustainableconnection.model.DiscardedItem;
import edu.iff.project.sustainableconnection.model.User;
import edu.iff.project.sustainableconnection.model.DropOffPoint;
import edu.iff.project.sustainableconnection.model.DiscardedItemCategory;
import edu.iff.project.sustainableconnection.repository.DiscardedItemRepository;
import edu.iff.project.sustainableconnection.repository.UserRepository;
import edu.iff.project.sustainableconnection.repository.DropOffPointRepository;
import edu.iff.project.sustainableconnection.repository.DiscardedItemCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DiscardedItemService {

    @Autowired
    private DiscardedItemRepository discardedItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DropOffPointRepository dropOffPointRepository;

    @Autowired
    private DiscardedItemCategoryRepository discardedItemCategoryRepository;

    public List<DiscardedItem> findAll() {
        return discardedItemRepository.findAll();
    }

    public Optional<DiscardedItem> findById(Long id) {
        return discardedItemRepository.findById(id);
    }

    public DiscardedItem save(Long userId, Long dropOffPointId, Long categoryId, int pointsEarned) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<DropOffPoint> dropOffPointOpt = dropOffPointRepository.findById(dropOffPointId);
        Optional<DiscardedItemCategory> categoryOpt = discardedItemCategoryRepository.findById(categoryId);

        if (userOpt.isEmpty() || dropOffPointOpt.isEmpty() || categoryOpt.isEmpty()) {
            throw new RuntimeException("Invalid ID(s) provided.");
        }

        DiscardedItem discardedItem = new DiscardedItem(userOpt.get(), dropOffPointOpt.get(), LocalDateTime.now(), pointsEarned, categoryOpt.get());
        return discardedItemRepository.save(discardedItem);
    }

    public DiscardedItem update(Long id, Long userId, Long dropOffPointId, Long categoryId, int pointsEarned) {
        if (!discardedItemRepository.existsById(id)) {
            return null;
        }

        Optional<User> userOpt = userRepository.findById(userId);
        Optional<DropOffPoint> dropOffPointOpt = dropOffPointRepository.findById(dropOffPointId);
        Optional<DiscardedItemCategory> categoryOpt = discardedItemCategoryRepository.findById(categoryId);

        if (userOpt.isEmpty() || dropOffPointOpt.isEmpty() || categoryOpt.isEmpty()) {
            throw new RuntimeException("Invalid ID(s) provided.");
        }

        DiscardedItem discardedItem = new DiscardedItem(userOpt.get(), dropOffPointOpt.get(), LocalDateTime.now(), pointsEarned, categoryOpt.get());
        discardedItem.setId(id);
        return discardedItemRepository.save(discardedItem);
    }

    public boolean delete(Long id) {
        if (discardedItemRepository.existsById(id)) {
            discardedItemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


