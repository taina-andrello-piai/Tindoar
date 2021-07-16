package com.techninjas.tindoar.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techninjas.tindoar.models.ERole;
import com.techninjas.tindoar.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer>{
	Optional<Role> findByName(ERole name);

}
