package com.example.user.service;

import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User saveUser(User user) {
        return repository.save(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }

    public User updateUser(Long id, User user) {
        User existing = getUserById(id);
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        return repository.save(existing);
    }

    public void deleteUser(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("User not found with id: " + id);
        }
        repository.deleteById(id);
    }

}
