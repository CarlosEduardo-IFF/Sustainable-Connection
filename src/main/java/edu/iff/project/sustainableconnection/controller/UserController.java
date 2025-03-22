package edu.iff.project.sustainableconnection.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import edu.iff.project.sustainableconnection.model.User;
import edu.iff.project.sustainableconnection.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/get/{id}")
    public User getById(Long id) {
        return userService.findById(id).get();
    }

    @PostMapping("/post")
       public ResponseEntity<User> create(@RequestParam String name, @RequestParam String email, @RequestParam String password) {

        User savedUser = userService.save(name, email, password);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<User> putMethodName(@PathVariable Long id, @RequestParam String name, @RequestParam String email, @RequestParam String password) {
        Optional<User> updatedUser = userService.update(id, name, email, password);
        return updatedUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());        
    }

      @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = userService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
