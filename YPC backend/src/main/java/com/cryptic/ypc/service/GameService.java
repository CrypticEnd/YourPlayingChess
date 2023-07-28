package com.cryptic.ypc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cryptic.ypc.game.rule.IGameRule;

@Service
public class GameService {
	// TODO initialize this list by getting all game types as a hash map <GameType,
	// IGameRUle>
	List<IGameRule> gameRules;

}
