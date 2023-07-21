package com.cryptic.ypc.dal;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cryptic.ypc.model.user.RegisteredUser;

@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Integer> {

	Optional<RegisteredUser> findByUsername(String username);

}
