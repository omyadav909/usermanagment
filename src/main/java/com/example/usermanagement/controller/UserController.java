package com.example.usermanagement.controller;

import com.example.usermanagement.entity.User;
import com.example.usermanagement.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }


    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        User existingUser = userRepository.findById(id).orElseThrow();
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "User deleted successfully";
    }
}
