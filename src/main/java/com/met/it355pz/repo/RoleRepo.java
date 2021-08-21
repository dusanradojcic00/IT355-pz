package com.met.it355pz.repo;

import com.met.it355pz.model.Role;
import com.met.it355pz.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(RoleType roleName);
}
