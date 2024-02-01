package com.alexbank.springsecbasic.service;

import com.alexbank.springsecbasic.entity.User;
import com.alexbank.springsecbasic.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepo userRepo,PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder= encoder;
    }

    public List<User> findAll(){
        return userRepo.findAll();
    }

    public Optional<User> findById(Long id){
        return userRepo.findById(id);
    }
    public Optional<User> findByUsername(String username){
        return this.userRepo.findByUsername(username);
    }

    public User insert(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public User update(User user) throws Exception {
        Optional<User> old = this.findById(user.getId());
        if (old.isPresent()){
            old.get().setUsername(user.getUsername());
            old.get().setPassword(encoder.encode(user.getPassword()));
            old.get().setName(user.getName());
            old.get().setRoles(user.getRoles());
        }else {
            throw new Exception("User Not Found Exception");
        }
        return this.userRepo.save(old.get());
    }

    public void delete(Long id){
        this.userRepo.deleteById(id);
    }


}
