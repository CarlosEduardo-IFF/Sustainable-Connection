package edu.iff.project.sustainableconnection.service;

import edu.iff.project.sustainableconnection.model.Client;
import edu.iff.project.sustainableconnection.model.DiscardedItem;
import edu.iff.project.sustainableconnection.model.DropOffPoint;
import edu.iff.project.sustainableconnection.model.User;
import edu.iff.project.sustainableconnection.model.DiscardedItemCategory;
import edu.iff.project.sustainableconnection.repository.DiscardedItemRepository;
import edu.iff.project.sustainableconnection.repository.DropOffPointRepository;
import edu.iff.project.sustainableconnection.repository.ClientRepository;
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
    private ClientRepository userRepository;

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

    public DiscardedItem save(String email, Long dropOffPointId, Long categoryId) {
        Client client = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("Client not found."));
    
        DropOffPoint dropOffPoint = dropOffPointRepository.findById(dropOffPointId)
            .orElseThrow(() -> new IllegalArgumentException("Drop-off point ID not found."));
    
        DiscardedItemCategory category = discardedItemCategoryRepository.findById(categoryId)
            .orElseThrow(() -> new IllegalArgumentException("Category ID not found."));
    
        int points = category.getPointsPerItens();
        client.setPoints(client.getPoints() + points);
        client.setCredits(client.getCredits() + points);
        userRepository.save(client);
    
        DiscardedItem discardedItem = new DiscardedItem(
            client, dropOffPoint, LocalDateTime.now(), points, category
        );
    
        return discardedItemRepository.save(discardedItem);
    }

    public boolean delete(Long id) {
        if (discardedItemRepository.existsById(id)) {
            discardedItemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<DiscardedItem> findByClientEmail(String email) {
        User client = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("Client not found."));
        
        Long idUser = client.getId();
        return discardedItemRepository.findByUserId(idUser);
    }
}


