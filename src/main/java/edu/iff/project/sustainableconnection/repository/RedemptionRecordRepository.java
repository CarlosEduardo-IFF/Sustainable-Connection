package edu.iff.project.sustainableconnection.repository;

import edu.iff.project.sustainableconnection.model.RedemptionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedemptionRecordRepository extends JpaRepository<RedemptionRecord, Long> {
	
}