package com.cryptic.ypc.game.rule;

import com.cryptic.ypc.game.BoardChange;
import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.model.GameType;
import com.cryptic.ypc.model.Move;
import com.cryptic.ypc.model.enums.Player;

public final class ChessVSConnectFour implements IGameRule {
	private ChessVSConnectFour INSTANCE;

	private ChessVSConnectFour() {
	}

	public ChessVSConnectFour getChessVSConnectFour() {
		if (INSTANCE == null)
			INSTANCE = new ChessVSConnectFour();

		return INSTANCE;
	}

	@Override
	public Move makeMove(String boardState, BoardChange move, Player playerTurn) {
		// TODO Auto-generated method stub
		return null;
	}

}
