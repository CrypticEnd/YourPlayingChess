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
 * @author reece
 *
 */
public interface IConnectFourMover extends IMover {

	/**
	 * For if the token can/cannot fall. Connect four tends to have tokens falling
	 * but concrete implementation could change that
	 * 
	 * @param boardState
	 * @param token
	 * @return
	 */
	public List<BoardChange> tokenFall(BoardState boardState, ConnectFourToken token, BoardChange move);

}
