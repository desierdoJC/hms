package io.nichan.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.nichan.hms.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{

    Role findByName(String name);

}
