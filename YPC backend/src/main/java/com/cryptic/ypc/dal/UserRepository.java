package com.cryptic.ypc.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cryptic.ypc.model.user.User;

public interface UserRepository extends JpaRepository<User, Long>{
	public boolean existsById(int id);
}
