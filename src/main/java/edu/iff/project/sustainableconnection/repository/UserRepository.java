package edu.iff.project.sustainableconnection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.iff.project.sustainableconnection.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
