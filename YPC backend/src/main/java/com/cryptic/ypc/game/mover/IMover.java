package com.cryptic.ypc.game.mover;

import com.cryptic.ypc.game.BoardChange;
import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.model.Move;

public interface IMover {

	/**
	 * Performs a single boardchange (move) if move is valid. Returns a list of boardchanges that the move caused
	 * ***
	 * e.g if moving a piece removes another piece or upgrades that is multiple changes
	 * @param boardState Current state of the board
	 * @param boardChange The change to be tested and performed 
	 * @return
	 */
	public Move move(BoardState boardState, BoardChange boardChange);
}
