package com.coseller.repository;

import org.springframework.data.repository.CrudRepository;

//import org.springframework.security.core.userdetails.User;
import com.coseller.domain.User;
//extends是继承
public interface UserRepository extends CrudRepository<User,Long>{
	//boot自己就知道这个函数的意思是根据domain里面user的username field来找到这个user
	User findByUsername(String username);
	
	User findByEmail(String email);

}
