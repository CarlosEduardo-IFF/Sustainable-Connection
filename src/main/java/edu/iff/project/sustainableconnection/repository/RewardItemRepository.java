package edu.iff.project.sustainableconnection.repository;

import edu.iff.project.sustainableconnection.model.RewardItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardItemRepository extends JpaRepository<RewardItem, Long> {
    }