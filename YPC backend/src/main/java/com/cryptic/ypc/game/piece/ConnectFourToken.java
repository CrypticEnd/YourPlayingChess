package com.cryptic.ypc.game.piece;

import java.util.List;

import com.cryptic.ypc.game.BoardChange;
import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.game.BoardPiece;
import com.cryptic.ypc.game.mover.IMover;

import lombok.ToString;

@ToString
public final class ConnectFourToken extends BoardPiece {
	private static ConnectFourToken INSTANCE;
	private static final byte id = 106;

	private ConnectFourToken() {
		this.name = "Token";
	}

	public static ConnectFourToken geToken() {
		if (INSTANCE == null) {
			INSTANCE = new ConnectFourToken();
		}

		return INSTANCE;
	}

	@Override
	public List<BoardChange> move(IMover mover, BoardState boardState, BoardChange move) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte getId() {
		return ConnectFourToken.id;
	}
}
