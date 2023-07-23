package com.cryptic.ypc.game.piece;

import com.cryptic.ypc.game.BoardChange;
import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.game.IBoardPiece;
import com.cryptic.ypc.game.mover.IMover;

import lombok.ToString;

@ToString
public final class ConnectFourToken implements IBoardPiece {
	private static ConnectFourToken INSTANCE;
	private final byte id = 106;
	private static final String name = "Token";

	private ConnectFourToken() {
	}

	public static ConnectFourToken geToken() {
		if (INSTANCE == null) {
			INSTANCE = new ConnectFourToken();
		}

		return INSTANCE;
	}

	@Override
	public Byte getId() {
		return id;
	}

	@Override
	public boolean move(IMover mover, BoardState boardState, BoardChange move) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		return name;
	}
}
