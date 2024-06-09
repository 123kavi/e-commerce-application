package com.java.store.store.repository;

import org.springframework.data.repository.CrudRepository;

import com.java.store.store.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	
	Role findByName(String name);

}
