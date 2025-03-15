package edu.iff.project.sustainableconnection.service;

import edu.iff.project.sustainableconnection.model.DiscardedItemCategory;
import edu.iff.project.sustainableconnection.repository.DiscardedItemCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscardedItemCategoryService {

    @Autowired
    private DiscardedItemCategoryRepository discardedItemCategoryRepository;

    public List<DiscardedItemCategory> findAll() {
        return discardedItemCategoryRepository.findAll();
    }

    public Optional<DiscardedItemCategory> findById(Long id) {
        return discardedItemCategoryRepository.findById(id);
    }

    public DiscardedItemCategory save(String name, int pointsPerItem) {
        DiscardedItemCategory category = new DiscardedItemCategory(name, pointsPerItem);
        return discardedItemCategoryRepository.save(category);
    }

    public DiscardedItemCategory update(Long id, String name, int pointsPerItem) {
        if (!discardedItemCategoryRepository.existsById(id)) {
            return null;
        }
        DiscardedItemCategory category = new DiscardedItemCategory(name, pointsPerItem);
        category.setId(id);
        return discardedItemCategoryRepository.save(category);
    }

    public boolean delete(Long id) {
        if (discardedItemCategoryRepository.existsById(id)) {
            discardedItemCategoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}