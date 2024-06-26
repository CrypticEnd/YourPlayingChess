package com.cryptic.ypc.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cryptic.ypc.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

}
