package edu.iff.project.sustainableconnection.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import edu.iff.project.sustainableconnection.model.User;
import edu.iff.project.sustainableconnection.repository.UserRepository;

public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User save(String name, String email, String password) {
        User user = new User(name, email, password);
        return userRepository.save(user);
    }

    public Optional<User> update(Long id, String name, String email, String password) {
        Optional<User> ExistingUser = userRepository.findById(id);

        if(ExistingUser.isPresent()) {
            User user = ExistingUser.get();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
