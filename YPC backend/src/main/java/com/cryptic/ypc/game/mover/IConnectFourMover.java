package com.cryptic.ypc.game.mover;

import java.util.List;

import com.cryptic.ypc.game.BoardChange;
import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.game.piece.connectFour.ConnectFourToken;

/**
 * This uses the Visitor pattern Returns a list of board changes It's own move
 * is one change, but concrete implementation could have removing a piece it
 * landed on or changing or having a cascading effect
 * 
 * @author Cryptic
 *
 */
public interface IConnectFourMover extends IMover {

	/**
	 * For if the token can/cannot be placed and/or if it should fall. Connect four tends to have tokens falling
	 * but concrete implementation could change that
	 * @param boardState The current state of the board
	 * @param token The token that should be checked
	 * @param move The move being made - Should only be a create move or null for checking if can fall
	 * @return
	 */
	public List<BoardChange> tokenFall(BoardState boardState, ConnectFourToken token, BoardChange move);

}
