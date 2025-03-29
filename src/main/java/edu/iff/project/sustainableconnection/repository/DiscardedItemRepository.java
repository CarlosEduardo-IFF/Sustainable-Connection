package edu.iff.project.sustainableconnection.repository;

import edu.iff.project.sustainableconnection.model.DiscardedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscardedItemRepository extends JpaRepository<DiscardedItem, Long> {
}
