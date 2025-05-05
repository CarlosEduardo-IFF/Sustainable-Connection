package edu.iff.project.sustainableconnection.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.iff.project.sustainableconnection.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByEmail(String email);
    // Retorna os 10 primeiros clientes ordenados por pontos em ordem decrescente
    List<Client> findTop10ByOrderByPointsDesc();
}
