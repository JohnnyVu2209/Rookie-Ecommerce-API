package com.musical.instrument.ecommerce.repositpory;

import com.musical.instrument.ecommerce.Entity.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musical.instrument.ecommerce.Entity.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByRole(ERole role);
}
