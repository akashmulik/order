package com.order.it.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.order.it.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>{

	@Modifying(clearAutomatically=true)
	@Query("update User u set u.address = ?1 where u.mobileNo = ?2")
	int updateAddrs(String address, String mobileNo);
}
