package edu.iff.project.sustainableconnection.repository;

import edu.iff.project.sustainableconnection.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
