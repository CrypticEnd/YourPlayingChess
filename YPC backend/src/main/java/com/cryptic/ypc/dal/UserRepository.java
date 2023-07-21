package com.cryptic.ypc.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.cryptic.ypc.model.user.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public boolean existsById(int id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE users SET type = 'Registered', user_name = :name, password = :password "
			+ " WHERE user_id = :id", nativeQuery = true)
	public void updateGuestUserToRegistered(@Param("id") Integer id, @Param("name") String username,
			@Param("password") String password);
}
