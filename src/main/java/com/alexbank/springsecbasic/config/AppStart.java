package com.alexbank.springsecbasic.config;

import com.alexbank.springsecbasic.entity.Role;
import com.alexbank.springsecbasic.entity.User;
import com.alexbank.springsecbasic.service.RoleService;
import com.alexbank.springsecbasic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
public class AppStart implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    @Override
    public void run(String... args) throws Exception {
        if (roleService.findAll().isEmpty()){
            roleService.insert(new Role(null,"ROLE_ADMIN"));
            roleService.insert(new Role(null,"ROLE_USER"));
        }

        if (userService.findAll().isEmpty()){
            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(roleService.findByName("ROLE_ADMIN"));

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(roleService.findByName("ROLE_USER"));

            userService.insert(new User(null,"14078","Mahmoud Rizk","123456",adminRoles));
            userService.insert(new User(null,"11030","Raghda Talaat","123456",userRoles));
        }
    }
}
