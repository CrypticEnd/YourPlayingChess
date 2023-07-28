package com.cryptic.ypc.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cryptic.ypc.model.Move;

@Repository
public interface MoveRepository extends JpaRepository<Move, Long> {

}
