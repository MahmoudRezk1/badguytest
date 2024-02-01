package com.alexbank.springsecbasic.repository;

import com.alexbank.springsecbasic.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByName (String name);
}
