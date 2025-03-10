package edu.iff.project.sustainableconnection.repository;

import edu.iff.project.sustainableconnection.model.DropOffPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DropOffPointRepository extends JpaRepository<DropOffPoint, Long> {
}

