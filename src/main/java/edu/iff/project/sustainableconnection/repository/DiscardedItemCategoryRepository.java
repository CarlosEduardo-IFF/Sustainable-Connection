package edu.iff.project.sustainableconnection.repository;

import edu.iff.project.sustainableconnection.model.DiscardedItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscardedItemCategoryRepository extends JpaRepository<DiscardedItemCategory, Long> {
}
