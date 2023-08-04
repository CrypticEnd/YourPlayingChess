package com.cryptic.ypc.game.mover;

import com.cryptic.ypc.game.BoardChange;
import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.model.Move;

public interface IMover {

	/**
	 * Performs a single boardchange (user move) is vaild given the game and returns a full move object
	 * e.g if moving a piece removes another piece or upgrades that is multiple changes
	 * @param boardState Current state of the board
	 * @param boardChange The change to be tested and performed 
	 * @return Move object with an order list of boardchanges. Or null if move was invalid
	 */
	public Move move(BoardState boardState, BoardChange boardChange);
}
