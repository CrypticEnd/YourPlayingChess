package com.cryptic.ypc.game.mover;

import com.cryptic.ypc.game.BoardChange;
import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.model.Move;

/**
 * Class containing movment rules for chess VS Connect four. 
 * @author Cryptic
 *
 */
public class ChessVSConnectFourMover extends ChessMover {

	public ChessVSConnectFourMover(boolean canTakePiece, boolean canTakeSelf, boolean startZeroZero) {
		super(canTakePiece, canTakeSelf, startZeroZero);
	}

	@Override
	public Move move(BoardState boardState, BoardChange boardChange) {
		// TODO Auto-generated method stub
		return super.move(boardState, boardChange);
	}
}
