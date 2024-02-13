package com.krawczyk.APItest.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.krawczyk.APItest.models.ERole;
import com.krawczyk.APItest.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
