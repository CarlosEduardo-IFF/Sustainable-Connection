package edu.iff.project.sustainableconnection.repository;

import edu.iff.project.sustainableconnection.model.DiscardedItem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscardedItemRepository extends JpaRepository<DiscardedItem, Long> {

    List<DiscardedItem> findByUserId(Long userId);
}
