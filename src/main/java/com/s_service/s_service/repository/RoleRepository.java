package com.s_service.s_service.repository;

import com.s_service.s_service.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(Role.UserRole role);
}
