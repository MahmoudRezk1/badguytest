package com.alexbank.springsecbasic.service;

import com.alexbank.springsecbasic.entity.Role;
import com.alexbank.springsecbasic.entity.User;
import com.alexbank.springsecbasic.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RoleService {
    private final RoleRepo roleRepo;

    @Autowired
    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public List<Role> findAll(){
        return roleRepo.findAll();
    }

    public Optional<Role> findById(Long id){
        return roleRepo.findById(id);
    }
    public Role findByName (String name){
        return roleRepo.findByName(name);
    }

    public Role insert(Role role){
        return roleRepo.save(role);
    }

    public Role update(Role role) throws Exception {
        Optional<Role> old = this.findById(role.getId());
        if (old.isPresent()){
            old.get().setName(role.getName());
        }else {
            throw new Exception("Role Not Found Exception");
        }
        return this.roleRepo.save(old.get());
    }

    public void delete(Long id){
        this.roleRepo.deleteById(id);
    }
}
