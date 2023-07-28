package com.cryptic.ypc.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cryptic.ypc.model.GameType;

@Repository
public interface GameTypeRepository extends JpaRepository<GameType, Byte> {

}
