package com.coseller.repository;

import org.springframework.data.repository.CrudRepository;


import com.coseller.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
	Role findByName(String name);
    
}
