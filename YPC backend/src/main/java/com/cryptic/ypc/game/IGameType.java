package com.cryptic.ypc.game;

import com.cryptic.ypc.model.Move;
import com.cryptic.ypc.model.enums.GameType;

public interface IGameType {

	public BoardState getBoardState();

	public GameType getType();

	/**
	 * @param boardState
	 * @param move       The same as the ypc.model.move.move. Char is two bytes long
	 *                   and expected to be in same format
	 * @return
	 */
	public Move makeMove(BoardState boardState, char move);

}
