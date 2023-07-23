package com.cryptic.ypc.game.piece.chess;

import java.util.List;

import com.cryptic.ypc.game.BoardChange;
import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.game.BoardPiece;
import com.cryptic.ypc.game.mover.IMover;

public final class Bishop extends BoardPiece {
	private static Bishop INSTANCE;
	private static final byte id = 101;

	private Bishop() {
		this.name = "Bishop";
	}

	public static Bishop getBishop() {
		if (INSTANCE == null) {
			INSTANCE = new Bishop();
		}

		return INSTANCE;
	}

	@Override
	public byte getId() {
		return Bishop.id;
	}

	@Override
	public List<BoardChange> move(IMover mover, BoardState boardState, BoardChange move) {
		// TODO Auto-generated method stub
		return null;
	}

}
