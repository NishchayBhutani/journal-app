package com.nishchay.journalApp.controller;

import com.nishchay.journalApp.builder.GetJournalResponseBuilder;
import com.nishchay.journalApp.builder.GetUserResponseBuilder;
import com.nishchay.journalApp.dto.GetUserResponse;
import com.nishchay.journalApp.entity.User;
import com.nishchay.journalApp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<GetUserResponse> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> findById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if(userOptional.isPresent()) {
            return new ResponseEntity<>(GetUserResponseBuilder.build(userOptional.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<GetUserResponse> create(@RequestBody User user) {
        user.setDate(LocalDateTime.now());
        userService.save(user);
        return new ResponseEntity<>(GetUserResponseBuilder.build(user), HttpStatus.CREATED);
    }

    @PutMapping("/{username}")
    public ResponseEntity<Void> update(@PathVariable String username, @RequestBody User user) {
        userService.update(username, user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
