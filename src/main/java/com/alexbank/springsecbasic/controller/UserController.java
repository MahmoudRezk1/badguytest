package com.alexbank.springsecbasic.controller;

import com.alexbank.springsecbasic.entity.User;
import com.alexbank.springsecbasic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        return this.userService.findAll();
    }
    @GetMapping(path = "/{id}")
    public Optional<User> findById(@PathVariable Long id) {
        return this.userService.findById(id);
    }
    @PostMapping
    public User insert(@RequestBody User user) {
        return this.userService.insert(user);
    }

    @PutMapping
    public User update(@RequestBody User user) throws Exception {
        return this.userService.update(user);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id){
        this.userService.delete(id);
    }
}
