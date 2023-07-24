package com.cryptic.ypc.game;

import com.cryptic.ypc.model.GameType;
import com.cryptic.ypc.model.Move;
import com.cryptic.ypc.model.enums.Player;

/**
 * Game implementation in most cases should be a singleton. It needs to store a
 * gametype, IMover and rules
 * 
 * Main functionality is to convert a single board change into many moves uses
 * the IMover
 * 
 * @author reece
 *
 */
public interface IGame {
	public BoardState getDefaultBoardState();

	public GameType getGameType();

	public Move makeMove(String boardState, BoardChange move, Player playerTurn);
}
