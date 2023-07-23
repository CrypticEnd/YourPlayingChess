package com.cryptic.ypc.game;

import com.cryptic.ypc.game.mover.IMover;
import com.cryptic.ypc.model.Move;
import com.cryptic.ypc.model.enums.GameType;
import com.cryptic.ypc.model.enums.Player;

/**
 * Game type implementation in most cases could be a singleton.
 * It needs to store:
 * 		- default board state, 
 * 		- Imover class concrete implementation
 * 		- A game type identifier 
 * 
 * 		Main functionality is to convert a single board change into many moves
 * 		uses the IMover 
 * 
 * @author reece
 *
 */
public interface IGameType {
	public BoardState getDefaultBoardState();
	public GameType getType();
	public IMover getIMover(IMover iMover);

	public Move makeMove(BoardState boardState, BoardChange move);


	
}
