package com.alexbank.springsecbasic.security;

import com.alexbank.springsecbasic.entity.User;
import com.alexbank.springsecbasic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private final UserService userService;
    @Autowired
    public AppUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userService.findByUsername(username);

        if (user.isEmpty()){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }else {
            AppUser appUser = new AppUser();
            appUser.setUsername(username);
            appUser.setPassword(user.get().getPassword());
            appUser.setRoles(user.get().getRoles());
        return appUser;
        }
    }
}
