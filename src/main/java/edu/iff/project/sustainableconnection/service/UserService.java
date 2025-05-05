package edu.iff.project.sustainableconnection.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.iff.project.sustainableconnection.DTO.ClientDTO;
import edu.iff.project.sustainableconnection.DTO.ClientDTOCreator;
import edu.iff.project.sustainableconnection.DTO.RegisterDTO;
import edu.iff.project.sustainableconnection.DTO.RegisterAdmDTO;
import edu.iff.project.sustainableconnection.model.Client;
import edu.iff.project.sustainableconnection.model.User;
import edu.iff.project.sustainableconnection.model.UserRole;
import edu.iff.project.sustainableconnection.repository.ClientRepository;
import edu.iff.project.sustainableconnection.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;
    
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public UserDetails findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<Client> update(Long id, int avatar, String name, String matriculation) {
        return clientRepository.findById(id).map(user -> {
            user.setName(name);
            user.setAvatar(avatar);
            user.setMatriculation(matriculation);
            return clientRepository.save(user);
        });
    }

    public boolean delete(Long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void register(RegisterDTO data) {
        if (userRepository.findByEmail(data.login()) != null) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new Client(
            data.name(),
            data.login(),
            encryptedPassword,
            UserRole.USER,
            data.matriculation(), 1
            
        );

        userRepository.save(newUser);
    }

    public void registerCreator(ClientDTOCreator data) {
        if (userRepository.findByEmail(data.login()) != null) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new Client(
            data.name(),
            data.login(),
            encryptedPassword,
            UserRole.USER,
            data.matriculation(), 
            data.avatar(),
            data.points(),
            data.credits()
            
        );

        userRepository.save(newUser);
    }

    public void registerAdm(RegisterAdmDTO data) {
        if (userRepository.findByEmail(data.login()) != null) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(
            data.name(),
            data.login(),
            encryptedPassword,
            UserRole.ADMIN
            
        );

        userRepository.save(newUser);
    }

    public Optional<Client> findClientById(Long id) {
        return clientRepository.findById(id);
    }

    public List<ClientDTO> getTop10ClientsByPoints() {
        return clientRepository.findTop10ByOrderByPointsDesc()
                .stream()
                .map(ClientDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public Optional<Client> findClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }
}
