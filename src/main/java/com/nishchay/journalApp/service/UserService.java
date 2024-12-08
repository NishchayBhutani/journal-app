package com.nishchay.journalApp.service;

import com.nishchay.journalApp.builder.GetUserResponseBuilder;
import com.nishchay.journalApp.dto.GetUserResponse;
import com.nishchay.journalApp.entity.User;
import com.nishchay.journalApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public List<GetUserResponse> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(GetUserResponseBuilder::build).toList();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User update(String username, User user) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isPresent()) {
            User currUser = userOptional.get();
            currUser.setUsername(user.getUsername());
            currUser.setPassword(user.getPassword());
            return userRepository.save(currUser);
        }
        return null;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
